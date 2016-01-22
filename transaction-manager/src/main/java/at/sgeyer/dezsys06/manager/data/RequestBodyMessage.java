package at.sgeyer.dezsys06.manager.data;

/**
 * This class extends the RequestMessage with a message body.
 * The body will contain the SQL String that is sent to the stations.
 *
 * @author Stefan Geyer
 * @version 20160122.1
 */
public class RequestBodyMessage extends RequestMessage {

    private RequestBody body;

    public RequestBodyMessage(String sender, Phase phase) {
        super(sender, phase);
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }

    public interface RequestBody {

        String getSQLString();

    }
}
