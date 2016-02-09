package at.sgeyer.dezsys06.manager;

import at.sgeyer.dezsys06.jms.Configuration;
import at.sgeyer.dezsys06.jms.Receiver;
import at.sgeyer.dezsys06.jms.Sender;
import org.apache.activemq.ActiveMQConnection;

public class Main {

    public static void main(String[] args) {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        Sender sender = new Sender(Configuration.getInstance().getRequestMessageTopicName());
        Receiver receiver = new Receiver(Configuration.getInstance().getResponseMessageTopicName());

//        Message message = new RequestMessage("manager", Message.Phase.PREPARE);
//        sender.broadcast(message);

        System.out.println(receiver.getMessages());

        sender.close();
        receiver.close();
    }
}