package at.sgeyer.dezsys06.manager.data;

public class ResponseMessage implements Message {

    private Phase phase;
    private Result result;
    private String sender;

    public ResponseMessage(String sender, Phase phase, Result result) {
        this.sender = sender;
        this.phase = phase;
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public Phase getPhase() {
        return phase;
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
    public Type getType() {
        return Type.RESPONSE;
    }

    public enum Result {
        POSITIVE,
        NEGATIVE,
        TIMEOUT
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + getType() +
                ", phase=" + getPhase() +
                ", result=" + result +
                '}';
    }
}