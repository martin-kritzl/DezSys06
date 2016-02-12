package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.jms.Configuration;
import at.sgeyer.dezsys06.station.dbms.DBConfiguration;
import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

public class Main {

    public Main(String[] args) {
        Configuration jmsConfig = Configuration.getInstance();
        jmsConfig.setUsername(ActiveMQConnection.DEFAULT_USER);
        jmsConfig.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        jmsConfig.setHost("tcp://192.168.30.200:61616");

        DBConfiguration dbConfig = DBConfiguration.getInstance();
        dbConfig.setName("mysql");
        dbConfig.setHost("localhost");
        dbConfig.setUsername("root");
        dbConfig.setPassword("root");
        dbConfig.setDatabase("dezsys06");
        dbConfig.setPort(3306);

        Logger logger = Logger.getLogger("Station");

        Station station = new Station(jmsConfig, dbConfig, logger);
        station.initialize();
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
