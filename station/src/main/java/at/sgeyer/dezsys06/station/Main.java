package at.sgeyer.dezsys06.station;

import at.sgeyer.dezsys06.manager.data.Message;
import at.sgeyer.dezsys06.manager.data.RequestBodyMessage;
import at.sgeyer.dezsys06.manager.data.ResponseMessage;
import at.sgeyer.dezsys06.manager.jms.Configuration;
import at.sgeyer.dezsys06.manager.jms.Receiver;
import at.sgeyer.dezsys06.manager.jms.Sender;
import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

public class Main {

    private DatabaseAccessor accessor;

    private Sender sender;
    private Receiver receiver;

    private String name;

    public Main(String[] args) {
        Configuration cfg = Configuration.getInstance();
        cfg.setUsername(ActiveMQConnection.DEFAULT_USER);
        cfg.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
        cfg.setHost("tcp://192.168.30.200:61616");

        this.accessor = new DatabaseAccessor("mysql", Logger.getRootLogger(), "localhost", "root", "12345", "restaurant", 3306);

        this.sender = new Sender(Configuration.getInstance().getResponseMessageTopicName());
        this.receiver = new Receiver(Configuration.getInstance().getRequestMessageTopicName(), new StationMessageCallback());
    }

    public static void main(String[] args) {
        new Main(args);
    }

    public class StationMessageCallback implements Receiver.Callback {

        @Override
        public void messageCallback(Message message) {
            if (message.getPhase() == Message.Phase.PREPARE) {
                Main.this.sender.broadcast(
                        new ResponseMessage(
                                Main.this.name,
                                Message.Phase.PREPARE,
                                ResponseMessage.Result.getResult(Main.this.accessor.canCommit())
                        )
                );
            } else if (message.getPhase() == Message.Phase.COMMIT) {
                if (message instanceof RequestBodyMessage) {
                    RequestBodyMessage.RequestBody body = ((RequestBodyMessage) message).getBody();
                    Main.this.sender.broadcast(
                            new ResponseMessage(
                                    Main.this.name,
                                    Message.Phase.COMMIT,
                                    ResponseMessage.Result.getResult(Main.this.accessor.executeSQLString(body.getSQLString()))
                            )
                    );
                }
            }
        }
    }
}
