package at.sgeyer.dezsys06.data;

public class CommitMessage implements Message {

    private boolean commit;

    public CommitMessage(boolean commit) {
        this.commit = commit;
    }

    @Override
    public Phase getPhase() {
        return Phase.COMMIT;
    }

    public boolean doCommit() {
        return commit;
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }
}
