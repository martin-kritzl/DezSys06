package at.sgeyer.dezsys06.manager.jms;

import at.sgeyer.dezsys06.manager.data.Message;

import javax.jms.*;

public class Sender {

    private Session session;
    private MessageProducer producer;

    public Sender() {
        try {
            this.session = ConnectionManager.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(Configuration.getInstance().getTopicName());

            this.producer = session.createProducer(destination);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Message message) {
        try {
            // Send the message to those who listen
            ObjectMessage objectMessage = session.createObjectMessage(message);
            producer.send(objectMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.producer.close();
            this.session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
