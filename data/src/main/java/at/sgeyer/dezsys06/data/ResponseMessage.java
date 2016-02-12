package at.sgeyer.dezsys06.data;

import java.util.UUID;

public class ResponseMessage implements Message {

    private UUID uuid;
    private Phase phase;
    private Result result;

    public ResponseMessage(Phase phase, UUID uuid, Result result) {
        this.uuid = uuid;
        this.phase = phase;
        this.result = result;
    }

    @Override
    public Phase getPhase() {
        return this.phase;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Result getResult() {
        return result;
    }

    public enum Result {
        ACKNOWLEDGED,
        NOT_ACKNOWLEDGED;

        public static Result getResult(boolean bool) {
            return bool ? ACKNOWLEDGED : NOT_ACKNOWLEDGED;
        }
    }
}
