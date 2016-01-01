package at.sgeyer.dezsys06.manager.data;

public class ResponseMessage implements Message {

    private Phase phase;
    private Result result;

    public ResponseMessage(Phase phase, Result result) {
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