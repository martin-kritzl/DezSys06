package at.sgeyer.dezsys06.manager.jms;

public class Configuration {

    private static Configuration instance;

    private String username;
    private String password;
    private String host;
    private String name;

    private String requestTopicName = "dezsys06_request";
    private String responseTopicName = "dezsys06_response";

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();

        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestTopicName() {
        return requestTopicName;
    }

    public void setRequestTopicName(String requestTopicName) {
        this.requestTopicName = requestTopicName;
    }

    public String getResponseTopicName() {
        return responseTopicName;
    }

    public void setResponseTopicName(String responseTopicName) {
        this.responseTopicName = responseTopicName;
    }
}
