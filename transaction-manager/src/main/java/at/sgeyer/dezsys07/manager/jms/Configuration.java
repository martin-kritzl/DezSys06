package at.sgeyer.dezsys07.manager.jms;

import org.apache.commons.lang3.Validate;

public class Configuration {

    private static Configuration instance;

    private String username;
    private String password;
    private String host;
    private String name;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();

        return instance;
    }

    public String getUsername() {
        Validate.notNull(this.username);
        return username;
    }

    public void setUsername(String username) {
        Validate.notNull(username);
        this.username = username;
    }

    public String getPassword() {
        Validate.notNull(this.password);
        return password;
    }

    public void setPassword(String password) {
        Validate.notNull(password);
        this.password = password;
    }

    public String getHost() {
        Validate.notNull(this.host);
        return host;
    }

    public void setHost(String host) {
        Validate.notNull(host);
        this.host = host;
    }

    public String getName() {
        Validate.notNull(this.name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
