package at.sgeyer.dezsys06.jms;

public class Configuration {

    private static Configuration instance;

    private String username;
    private String password;
    private String host;
    private String name;

    private String requestMessageTopicName = "dezsys06_request";
    private String responseMessageTopicName = "dezsys06_response";

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

    public String getRequestMessageTopicName() {
        return requestMessageTopicName;
    }

    public void setRequestMessageTopicName(String requestMessageTopicName) {
        this.requestMessageTopicName = requestMessageTopicName;
    }

    public String getResponseMessageTopicName() {
        return responseMessageTopicName;
    }

    public void setResponseMessageTopicName(String responseMessageTopicName) {
        this.responseMessageTopicName = responseMessageTopicName;
    }
}
