package at.sgeyer.dezsys06.manager.data;

public class RequestMessage implements Message {

    @Override
    public Type getType() {
        return Type.REQUEST;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + getType() +
                "}";
    }
}
