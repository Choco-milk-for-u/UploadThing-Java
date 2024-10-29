package org.chocodev.UploadThing.Constants;
public enum KeyType {
    FILE_KEY("fileKeys"),
    CUSTOM_ID("customIds");

    private final String value;

    // Constructor to set the string value for each enum constant
    KeyType(String value) {
        this.value = value;
    }

    // Getter to retrieve the string value
    public String getValue() {
        return value;
    }
}

