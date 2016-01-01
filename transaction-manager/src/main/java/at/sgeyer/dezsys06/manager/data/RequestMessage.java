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

    public RequestMessage(Phase phase) {
        this.phase = phase;
    }

    @Override
    public Type getType() {
        return Type.REQUEST;
    }

    @Override
    public Phase getPhase() {
        return this.phase;
    }

    @Override
    public String toString() {
        return "Message{" +
                "phase=" + getPhase() +
                ", type=" + getType() +
                "}";
    }
}
