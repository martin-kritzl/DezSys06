package at.sgeyer.dezsys06.station.dbms;

import org.apache.log4j.Logger;

/**
 * Abstract child of DBMS. This class is the superclass of all DBMS that are hosted on a remote server
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public abstract class HostnameDBMS extends DBMS {
    private String hostname = "localhost";
    private String username = "root";
    private String password = "";
    private String database = "";

    private int port = 0;


    public HostnameDBMS(Logger log, String hostname, String username, String password, String database, int port) {
        super(log);
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    /**
     * Exception will be thrown if the hostname is null or empty
     *
     * @param hostname The new hostname
     */
    public void setHostname(String hostname) throws DBMSException {
        if (hostname == null || hostname.length() == 0)
            throw new DBMSException("Hostname cannot be null or empty");
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    /**
     * Exceiption will bee thrown if the port number is negative
     *
     * @param port The new port number
     */
    public void setPort(int port) throws DBMSException {
        if (port < 0)
            throw new DBMSException("Port number cannot be negative");
        this.port = port;
    }

    public String getUsername() {
        return this.username;
    }

    /**
     * Exception will be thrown if the username is null or empty
     *
     * @param username The new username
     */
    public void setUsername(String username) throws DBMSException {
        if (username == null)
            throw new DBMSException("Username cannot be null");
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Exception will be thrown if the password is null or empty
     *
     * @param password The new password
     */
    public void setPassword(String password) throws DBMSException {
        if (password == null)
            throw new DBMSException("Password cannot be null");
        this.password = password;
    }

    public String getDatabase() {
        return this.database;
    }

    /**
     * Exception will be thrown if the database name is null or empty
     *
     * @param database The new database name
     */
    public void setDatabase(String database) throws DBMSException {
        if (database == null || database.length() == 0)
            throw new DBMSException("Database cannot be null or empty");
        this.database = database;
    }
}
