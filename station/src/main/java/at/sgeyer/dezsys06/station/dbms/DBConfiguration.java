package at.sgeyer.dezsys06.station.dbms;

public class DBConfiguration {

    private static DBConfiguration instance;

    private String name, host, username, password, database;
    private int port;

    private DBConfiguration() {}

    public static DBConfiguration getInstance() {
        if (instance == null)
            instance = new DBConfiguration();
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {
        return port;
    }
}
