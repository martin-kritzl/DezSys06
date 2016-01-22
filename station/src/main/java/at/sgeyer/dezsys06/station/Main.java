package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.manager.data.Message;
import at.sgeyer.dezsys06.manager.jms.Configuration;
import at.sgeyer.dezsys06.manager.jms.Receiver;
import at.sgeyer.dezsys06.manager.jms.Sender;
import org.apache.activemq.ActiveMQConnection;

public class Main {

    public Main() {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        Sender sender = new Sender(Configuration.getInstance().getResponseMessageTopicName());
        Receiver receiver = new Receiver(Configuration.getInstance().getRequestMessageTopicName(), new StationMessageCallback());
    }

    public static void main(String[] args) {
        new Main();
    }

    public class StationMessageCallback implements Receiver.Callback {

        @Override
        public void messageCallback(Message message) {

        }
    }
}
