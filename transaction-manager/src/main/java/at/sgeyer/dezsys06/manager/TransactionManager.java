package at.sgeyer.dezsys06.manager;

import at.sgeyer.dezsys06.data.InitMessage;
import at.sgeyer.dezsys06.data.Message;
import at.sgeyer.dezsys06.data.ResponseMessage;
import at.sgeyer.dezsys06.jms.Configuration;
import at.sgeyer.dezsys06.jms.Receiver;
import at.sgeyer.dezsys06.jms.Sender;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionManager {

    private List<Station> stations;

    private Sender sender;
    private Receiver receiver;

    private Logger logger;

    public TransactionManager(Configuration configuration, Logger logger) {
        this.stations = new ArrayList<>();
        this.logger = logger;

        this.sender = new Sender(configuration.getRequestMessageTopicName());
        this.receiver = new Receiver(configuration.getResponseMessageTopicName(), message -> {
            if (message.getPhase() == Message.Phase.INIT) {
                initCallback((InitMessage) message);
            } else if (message instanceof ResponseMessage) {
                ResponseMessage responseMessage = (ResponseMessage) message;
                if (responseMessage.getPhase() == Message.Phase.PREPARE) {
                    prepareCallback(responseMessage);
                } else if (responseMessage.getPhase() == Message.Phase.COMMIT) {
                    commitCallback(responseMessage);
                }
            }
        });
    }

    private void initCallback(InitMessage message) {
        Station station = new Station(message.getUuid());
        this.stations.add(station);
        this.logger.info("Added the station " + station.getUuid() + " after receiving a new InitMessage");
    }

    private void prepareCallback(ResponseMessage message) {
        Optional<Station> optionalStation = this.stations.stream().filter(station -> station.getUuid().equals(message.getUuid())).findFirst();

        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
            station.setCanCommit(message.getResult() == ResponseMessage.Result.ACKNOWLEDGED);
            this.logger.info("The station " + station.getUuid() + " set its canCommit state to " + station.getCanCommit());
        } else {
            this.logger.info("Received a prepare message from an unknown station: " + message.getUuid());
        }
    }

    private void commitCallback(ResponseMessage message) {
        Optional<Station> optionalStation = this.stations.stream().filter(station -> station.getUuid().equals(message.getUuid())).findFirst();

        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
            station.setFinished(message.getResult() == ResponseMessage.Result.ACKNOWLEDGED);
            this.logger.info("The station " + station.getUuid() + " set its finished state to " + station.getCanCommit());
        } else {
            this.logger.info("Received a commit message from an unknown station: " + message.getUuid());
        }
    }

    public void close() {
        this.sender.close();
        this.receiver.close();
    }
}
