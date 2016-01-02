package at.sgeyer.dezsys06.manager;

import at.sgeyer.dezsys06.manager.data.Message;
import at.sgeyer.dezsys06.manager.data.RequestMessage;
import at.sgeyer.dezsys06.manager.jms.Configuration;
import at.sgeyer.dezsys06.manager.jms.Receiver;
import at.sgeyer.dezsys06.manager.jms.Sender;
import org.apache.activemq.ActiveMQConnection;

public class Main {

    public static void main(String[] args) {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        Sender sender = new Sender(Configuration.getInstance().getRequestMessageTopicName());
        Receiver receiver = new Receiver(Configuration.getInstance().getResponseMessageTopicName());

        Message message = new RequestMessage(Message.Phase.PREPARE);
        sender.broadcast(message);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(receiver.getMessages());

        sender.close();
        receiver.close();
    }
}