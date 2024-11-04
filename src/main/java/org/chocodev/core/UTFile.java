package org.chocodev.core;

public class UTFile {
    private String name;
    private byte[] data;
    private long size;

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setBytes(byte[] bytes) {
        this.data = bytes;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getBytes() {
        return this.data;
    }

    public long getFileSize() {
        return this.size;
    }
}
