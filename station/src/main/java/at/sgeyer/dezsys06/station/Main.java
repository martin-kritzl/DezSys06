package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.data.*;
import at.sgeyer.dezsys06.jms.Configuration;
import at.sgeyer.dezsys06.jms.Receiver;
import at.sgeyer.dezsys06.jms.Sender;
import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

import java.util.UUID;

public class Main {

    private DatabaseAccessor accessor;

    private Sender sender;
    private Receiver receiver;

    private UUID uuid = UUID.randomUUID();

    public Main(String[] args) {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        this.accessor = new DatabaseAccessor("mysql", Logger.getRootLogger(), "localhost", "root", "12345", "restaurant", 3306);

        this.sender = new Sender(Configuration.getInstance().getResponseMessageTopicName());
        this.receiver = new Receiver(Configuration.getInstance().getRequestMessageTopicName(), message -> {
            if (message.getPhase() == Message.Phase.PREPARE) {

                PrepareMessage prepareMessage = (PrepareMessage) message;

                this.sender.broadcast(
                        new ResponseMessage(
                                Message.Phase.PREPARE,
                                this.uuid,
                                ResponseMessage.Result.getResult(this.accessor.executeSQLString(prepareMessage.getSqlString()))
                        )
                );
            } else if (message.getPhase() == Message.Phase.COMMIT) {
                CommitMessage commitMessage = (CommitMessage) message;

                this.sender.broadcast(
                        new ResponseMessage(
                                Message.Phase.COMMIT,
                                this.uuid,
                                ResponseMessage.Result.getResult(this.accessor.finishTransaction(commitMessage.doCommit()))
                        )
                );
            }
        });

        this.sender.broadcast(new InitMessage(this.uuid));
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
