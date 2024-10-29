package org.chocodev.UploadThing.File;

public class FileData implements IFile {
    private final byte[] bytes;
    private final String name;
    private final String contentType;
    private final String fileExtension;

    public FileData(String name, String contentType, byte[] bytes) {
        this.bytes = bytes;
        this.name = name;
        this.contentType = contentType;
        this.fileExtension = name.substring(name.lastIndexOf(".") + 1);
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.contentType;
    }

    @Override
    public String getFileExtension() {
        return this.fileExtension;
    }

}
