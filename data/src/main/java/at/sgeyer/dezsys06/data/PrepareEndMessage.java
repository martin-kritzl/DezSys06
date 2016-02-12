package at.sgeyer.dezsys06.data;

public class PrepareEndMessage implements Message {
    @Override
    public Phase getPhase() {
        return Phase.PREPARE;
    }
}