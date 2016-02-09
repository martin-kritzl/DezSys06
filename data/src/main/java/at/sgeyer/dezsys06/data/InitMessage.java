package at.sgeyer.dezsys06.data;

import java.util.UUID;

public class InitMessage implements Message {

    private UUID uuid;

    public InitMessage(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public Phase getPhase() {
        return Phase.INIT;
    }
}
