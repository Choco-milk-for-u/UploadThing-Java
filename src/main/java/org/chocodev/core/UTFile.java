package org.chocodev.core;

public class UTFile {
    private String name;
    private byte[] data;
    private long size;
    private long lastModified;
    private String type;

    private UTFile(){}
    public String getName() {
        return this.name;
    }

    public byte[] getBytes() {
        return this.data;
    }

    public long getFileSize() {
        return this.size;
    }

    public String getType() {
        return type;
    }

    public long getLastModified() {
        return lastModified;
    }

    public static Bulder bulder() {
        return new Bulder();
    }

    public static class Bulder {
        private final UTFile File = new UTFile();

        private Bulder() {
        }

        public UTFile build() {
            return File;
        }
        public Bulder setLastModified(long lastModified) {
            File.lastModified = lastModified;
            return this;

        }
        public Bulder setType(String type) {
            File.type = type;
            return this;

        }
        public Bulder setName(String name) {
            File.name = name;
            return this;

        }
        public Bulder setSize(long size) {
            File.size = size;
            return this;

        }
        public Bulder setBytes(byte[] bytes) {
            File.data = bytes;
            return this;
        }
    }

}