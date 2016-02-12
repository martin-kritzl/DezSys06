package at.sgeyer.dezsys06.manager;

import java.util.UUID;

public class Station {

    private UUID uuid;
    private Boolean canCommit;
    private Boolean finished;

    public Station(UUID uuid) {
        this.uuid = uuid;
        this.canCommit = null;
        this.finished = null;
    }

    public Station(UUID uuid, Boolean canCommit, Boolean finished) {
        this.uuid = uuid;
        this.canCommit = canCommit;
        this.finished = finished;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getCanCommit() {
        return canCommit;
    }

    public void setCanCommit(Boolean canCommit) {
        this.canCommit = canCommit;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
