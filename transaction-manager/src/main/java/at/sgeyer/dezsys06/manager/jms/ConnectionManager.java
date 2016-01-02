package at.sgeyer.dezsys06.manager.jms;


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
                    Configuration.getInstance().getHost()
            );

            // Create the connection with the given data and start it
            c = connectionFactory.createConnection();
            c.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO use catch clause
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
