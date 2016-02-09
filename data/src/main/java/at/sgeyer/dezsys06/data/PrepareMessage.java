package at.sgeyer.dezsys06.data;

public class PrepareMessage implements Message {

    private String sql;

    public PrepareMessage(String sql) {
        this.sql = sql;
    }

    @Override
    public Phase getPhase() {
        return Phase.PREPARE;
    }

    public String getSqlString() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
