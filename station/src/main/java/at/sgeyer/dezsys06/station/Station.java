package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.data.*;
import at.sgeyer.dezsys06.jms.Configuration;
import at.sgeyer.dezsys06.jms.Receiver;
import at.sgeyer.dezsys06.jms.Sender;
import at.sgeyer.dezsys06.station.dbms.DBConfiguration;
import at.sgeyer.dezsys06.station.dbms.DatabaseAccessor;
import org.apache.log4j.Logger;

import java.util.UUID;

public class Station {

    private UUID uuid;

    private Sender sender;
    private Receiver receiver;

    private DatabaseAccessor accessor;

    private Logger logger;

    private Message.Phase current;
    private boolean sqlSuccess = true;

    public Station(Configuration configuration, DBConfiguration dbConfiguration, Logger logger) {
        this.uuid = UUID.randomUUID();
        this.current = Message.Phase.PREPARE;

        this.logger = logger;

        this.logger.info("Initializing connections");

        this.accessor = new DatabaseAccessor(
                dbConfiguration.getName(), this.logger, dbConfiguration.getHost(),
                dbConfiguration.getUsername(), dbConfiguration.getPassword(),
                dbConfiguration.getDatabase(), dbConfiguration.getPort());

        this.sender = new Sender(configuration.getResponseMessageTopicName());
        this.receiver = new Receiver(configuration.getRequestMessageTopicName(), message -> {
            if (message.getPhase() == Message.Phase.PREPARE) {

                if (message instanceof PrepareEndMessage) {
                    prepareEndCallback((PrepareEndMessage) message);
                } else {
                    prepareCallback((PrepareMessage) message);
                }
            } else if (message.getPhase() == Message.Phase.COMMIT) {
                commitCallback((CommitMessage) message);
            }
        });

        this.logger.info("Station " + this.uuid + " was started successfully");
    }

    private void prepareCallback(PrepareMessage message) {
        this.sender.broadcast(
                new ResponseMessage(
                        Message.Phase.PREPARE,
                        this.uuid,
                        ResponseMessage.Result.getResult(this.accessor.executeSQLString(message.getSqlString()))
                )
        );
    }

    private void prepareEndCallback(PrepareEndMessage message) {
        this.logger.info("Received PrepareEndMessage: Entering COMMIT Phase");
        this.sender.broadcast(
                new ResponseMessage(
                        Message.Phase.PREPARE,
                        this.uuid,
                        ResponseMessage.Result.getResult(this.sqlSuccess)
                )
        );

        this.current = Message.Phase.COMMIT;
    }

    private void commitCallback(CommitMessage message) {
        new ResponseMessage(
                Message.Phase.PREPARE,
                this.uuid,
                ResponseMessage.Result.getResult(this.accessor.finishTransaction(message.doCommit()))
        );
    }

    public void initialize() {
        this.sender.broadcast(new InitMessage(this.uuid));
        this.logger.info("Sent InitMessage to the listening TransactionManager");
    }

    public void close() {
        this.logger.info("Attempting to close open connections");
        this.sender.close();
        this.receiver.close();
        this.accessor.close();
    }

}
