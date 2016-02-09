package at.sgeyer.dezsys06.data;

import java.io.Serializable;

public interface Message extends Serializable {

    Phase getPhase();

    enum Phase {
        INIT,
        PREPARE,
        COMMIT
    }
}
