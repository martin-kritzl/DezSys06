package at.sgeyer.dezsys06.station.dbms;

import org.apache.log4j.Logger;

/**
 * This class returns implementations of DBMS for the type related arguments
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class DBMSFactory {

    /**
     * @param dbmsName Name of the DBMS, not case sensitive
     * @param log The logger where the database should log to
     * @param hostname The remote host of the database
     * @param username The username of the database
     * @param password The password of the password
     * @param database The name of the database
     * @param port The port where the database is located
     * @return Returns an implementation of HostnameDBMS for the given name
     */
    public static HostnameDBMS getHostnameDBMS(String dbmsName, Logger log, String hostname, String username, String password, String database, int port) {
        switch (dbmsName.toLowerCase()) {
            case "mysql": return new MySQL(log, hostname, username, password, database, port, "utf8");
            case "postgresql": return new PostgreSQL(log, hostname, username, password, database, port, "utf8");
            // No further implementations yet
            default: return null;
        }
    }

    /**
     * @param dbmsName Name of the DBMS, not case sensitive
     * @param log The logger where the database should log to
     * @param directory The directory of the database file
     * @param filename The filename of the database file
     * @return Returns an implementation of FilebasedDBMS for the given name
     */
    public static FilebasedDBMS getFilebasedDBMS(String dbmsName, Logger log, String directory, String filename) {
        switch (dbmsName.toLowerCase()) {
            // No implementations yet
            default: return null;
        }
    }
}
