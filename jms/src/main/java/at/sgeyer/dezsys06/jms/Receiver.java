package at.sgeyer.dezsys06.jms;

import at.sgeyer.dezsys06.data.Message;

import javax.jms.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Receiver implements Runnable {

    private List<Message> messages;

    private boolean lookForMessages = true;

    private Session session;
    private MessageConsumer consumer;

    private Callback callback;

    public Receiver(String topic) {
        // Concurrent implementation
        this.messages = new CopyOnWriteArrayList<>();

        try {
            this.session = ConnectionManager.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = this.session.createTopic(topic);

            this.consumer = this.session.createConsumer(destination);

        } catch (JMSException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    public Receiver(String topic, Callback callback) {
        this(topic);
        this.callback = callback;
    }

    public void run() {
        while (this.lookForMessages) {
            try {
                // Receive Method
                ObjectMessage objectMessage = (ObjectMessage) consumer.receive();
                if (objectMessage != null) {
                    Message message = (Message) objectMessage.getObject();

                    this.messages.add(message);

                    if (this.callback != null)
                        this.callback.messageCallback(message);

                    // Message was received successfully
                    objectMessage.acknowledge();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.lookForMessages = false;

        try {
            this.consumer.close();
            this.session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public interface Callback {
        void messageCallback(Message message);
    }
}
