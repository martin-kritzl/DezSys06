package at.sgeyer.dezsys06.manager.data;

/**
 * Message to request information from
 * a station in a specific phase.
 *
 * @author Stefan Geyer
 * @version 20160101
 */
public class RequestMessage implements Message {

    private Phase phase;
    private String sender;

    public RequestMessage(String sender, Phase phase) {
        this.phase = phase;
        this.sender = sender;
    }

    @Override
    public Type getType() {
        return Type.REQUEST;
    }

    @Override
    public Phase getPhase() {
        return this.phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + getType() +
                ", sender=" + getSender() +
                ", phase=" + getPhase() +
                "}";
    }
}
