package at.sgeyer.dezsys06.jms;

import at.sgeyer.dezsys06.data.Message;

import javax.jms.*;

public class Sender {

    private Session session;
    private MessageProducer producer;

    public Sender(String topic) {
        try {
            this.session = ConnectionManager.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);

            this.producer = session.createProducer(destination);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Message message) {
        try {
            // Send the message to those who listen to the topic
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