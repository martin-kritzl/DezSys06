package at.sgeyer.dezsys06.manager.data;

import java.io.Serializable;

/**
 *
 * @author Stefan Geyer
 * @version 20160101
 */
public interface Message extends Serializable {

    /**
     * Determines which phase the result is about
     *
     * @return The type of the message. This may be hardcoded
     */
    Type getType();

    /**
     * The type of the message so a received message can be identified
     *
     * @return The phase of the message
     */
    Phase getPhase();

    enum Phase {
        PREPARE,
        COMMIT
    }

    enum Type {
        REQUEST,
        RESPONSE
    }
}
