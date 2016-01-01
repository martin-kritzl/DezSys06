package at.sgeyer.dezsys06.manager.data;

import java.io.Serializable;

public interface Message extends Serializable {

    Type getType();

    Phase getPhase();

    /**
     * Determines which phase the result is about
     */
    enum Phase {
        PREPARE,
        COMMIT
    }

    /**
     * The type of the message so a received message can be identified
     */
    enum Type {
        REQUEST,
        RESPONSE
    }
}
