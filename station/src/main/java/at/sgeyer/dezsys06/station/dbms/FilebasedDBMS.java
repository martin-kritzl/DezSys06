package at.sgeyer.dezsys06.station.dbms;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Abstract child of DBMS. This class is the superclass of all DBMS that are stored in a file.
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public abstract class FilebasedDBMS extends DBMS {

    private String directory;
    private String filename;
    private File file;
    private String extension = ".db";

    public FilebasedDBMS(Logger log, String directory, String filename) {
        super(log);
        try {
            setFile(directory, filename);
        } catch (DBMSException e) {
            getLogger().error("Unable to set filename.");
        }
    }

    public FilebasedDBMS(Logger log, String directory, String filename, String extension) {
        super(log);
        try {
            setFile(directory, filename, extension);
        } catch (DBMSException e) {
            getLogger().error("Unable to set filename.");
        }
    }

    public String getDirectory() {
        return directory;
    }

    /**
     * Exception will be thrown if the directory is null or empty
     *
     * @param directory The new directory
     */
    public void setDirectory(String directory) throws DBMSException {
        if (directory == null || directory.length() == 0)
            throw new DBMSException("Directory cannot be null or empty.");
        else
            this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    /**
     * Exception will be thrown if the filename is null or empty<br>
     * Exception will be thrown if the filename contains one of the following strings:
     * <ul>
     * <li>/</li>
     * <li>\</li>
     * <li>.db</li>
     * </ul>
     *
     * @param filename The new filename
     */
    public void setFilename(String filename) throws DBMSException {
        if (filename == null || filename.length() == 0)
            throw new DBMSException("Filename cannot be null or empty.");
        else if (filename.contains("/") || filename.contains("\\") || filename.endsWith(".db"))
            throw new DBMSException("The database filename cannot contain: /, \\, or .db");
        else
            this.filename = filename;
    }

    public String getExtension() {
        return extension;
    }

    /**
     * Exception will be thrown if the extension is null or empty<br>
     * Exception will be thrown if the extension does not begin with a period
     *
     * @param extension The new file extention
     */
    public void setExtension(String extension) throws DBMSException {
        if (extension == null || extension.length() == 0)
            throw new DBMSException("Extension cannot be null or empty.");
        if (extension.charAt(0) != '.')
            throw new DBMSException("Extension must begin with a period");
    }

    public File getFile() {
        return this.file;
    }

    public void setFile() {
        file = null;
    }

    public void setFile(String directory, String filename) throws DBMSException {
        setDirectory(directory);
        setFilename(filename);

        File folder = new File(getDirectory());
        if (!folder.exists())
            folder.mkdir();

        file = new File(folder.getAbsolutePath() + File.separator + getFilename() + getExtension());
    }

    public void setFile(String directory, String filename, String extension) throws DBMSException {
        setExtension(extension);
        this.setFile(directory, filename);
    }
}