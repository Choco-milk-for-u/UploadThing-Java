package org.chocodev.uploadthing.core.api;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.uploadthing.core.internal.Messages;
import org.chocodev.uploadthing.core.util.ParametersValidator;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UTFile {
    @NotEmpty
    private String name;
    @NotNull
    private byte[] data;
    @Positive
    private long size;
    private long lastModified = System.currentTimeMillis();
    private String type = "application/octet-stream";
    private String customId;

    private UTFile() {
    }

    public String getName() {
        return this.name;
    }

    public String getCustomId() {
        return this.customId;
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
            ParametersValidator.validate(new BadApiCallException(Messages.BAD_BUILDER_BUILD), File);
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

        public Bulder setCustomId(String customId) {
            File.customId = customId;
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
