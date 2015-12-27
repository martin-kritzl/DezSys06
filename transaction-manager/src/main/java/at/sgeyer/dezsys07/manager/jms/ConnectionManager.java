package at.sgeyer.dezsys07.manager.jms;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.Validate;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

public class ConnectionManager {

    private static Connection connection;

    private static Connection createConnection() {
        Connection c = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    Configuration.getInstance().getUsername(),
                    Configuration.getInstance().getPassword(),
                    Configuration.getInstance().getHost());

            // Create the connection with the given data
            c = connectionFactory.createConnection();
            c.start();

//        //Receiver und Sender initialisieren
//        this.reciever = new ChatReceiver(this.connection, conf.getSystemName(), this.username);
//        this.sender = new ChatSender(this.connection, conf.getSystemName());
//
//        //Receiver-Thread starten
//        new Thread(this.reciever).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Make sure no exception occurred
        Validate.notNull(c);

        return c;
    }

    public static Connection getConnection() {
        if (connection == null)
            connection = createConnection();
        return connection;
    }
}