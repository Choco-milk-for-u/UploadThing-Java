package org.chocodev.uploadthing.core.util.Constants;
public enum KeyType {
    FILE_KEY("fileKeys"),
    CUSTOM_ID("customIds");

    private final String value;
    KeyType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

