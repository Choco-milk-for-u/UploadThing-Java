package org.chocodev.uploadthing.core.api.File;

public interface IUTFile {
    /**
     * @return custom id of the file.
     */
    public String getCustomId();

    /**
     * @return file key of the file.
     */
    public String getFileKey();
    /**
     * @return name of the file.
     */
    public String getFileName();

}
