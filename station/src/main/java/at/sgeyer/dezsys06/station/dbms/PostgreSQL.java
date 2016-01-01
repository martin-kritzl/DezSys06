package at.sgeyer.dezsys06.station.dbms;

import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

class PostgreSQL extends HostnameDBMS {

    private String charset;

    public PostgreSQL(Logger log, String hostname, String username, String password, String database, int port, String charset) {
        super(log, hostname, username, password, database, port);
        this.charset = charset;
    }

    @Override
    boolean initialize() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            return true;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            getLogger().error("PostgreSQL DataSource class missing: " + e.getMessage() + ".");
        }
        return false;
    }

    @Override
    public boolean open() {
        try {
            if (initialize()) {
                this.connection = DriverManager.getConnection("jdbc:postgresql://" + getHostname() + ":" + getPort() + "/" + getDatabase() + (this.charset != null && this.charset.length() > 0 ? "?charSet=" + this.charset : ""), getUsername(), getPassword());
                return true;
            }
        } catch (SQLException e) {
            this.getLogger().error(e.getMessage());
        }

        return false;
    }
}
