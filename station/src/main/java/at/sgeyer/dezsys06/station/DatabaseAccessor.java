package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.station.dbms.DBMS;
import at.sgeyer.dezsys06.station.dbms.DBMSException;
import at.sgeyer.dezsys06.station.dbms.DBMSFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseAccessor {

    private DBMS database;

    public DatabaseAccessor(String databaseName, Logger logger, String hostname,
                            String username, String password, String database, int port) {
        this.database = DBMSFactory.getHostnameDBMS(databaseName, logger, hostname, username, password, database, port);
    }

    public boolean executeSQLString(String sql) {
        try {
            this.database.getConnection().setAutoCommit(false);
            this.database.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            this.database.query(sql);
            return true;
        } catch (DBMSException | SQLException e) {
            //TODO
            e.printStackTrace();
            return false;
        }
    }

    public boolean finishTransaction(boolean commit) {
        try {
            if (commit) {
                this.database.getConnection().commit();
            } else {
                this.database.getConnection().rollback();
            }

            this.database.getConnection().setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
            return false;
        }
    }

}
