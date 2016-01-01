package at.sgeyer.dezsys06.station.dbms;

/**
 * Exception that will be thrown if a DBMS method was called without the DBMS being connected
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class DBMSException extends Exception {

    private String message;

    public DBMSException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
