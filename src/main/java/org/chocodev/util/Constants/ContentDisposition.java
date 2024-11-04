package org.chocodev.util.Constants;

public enum ContentDisposition {
    PUBLIC("attachment"),
    PRIVATE("inline");

    private final String value;

    ContentDisposition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
