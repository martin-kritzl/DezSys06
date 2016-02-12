package at.sgeyer.dezsys06.manager;

import at.sgeyer.dezsys06.jms.Configuration;
import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

public class Main {

    public Main(String[] args) {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        Logger logger = Logger.getLogger("TransactionManager");

        TransactionManager manager = new TransactionManager(cfg, logger);
    }

    public static void main(String[] args) {
        new Main(args);
    }
}