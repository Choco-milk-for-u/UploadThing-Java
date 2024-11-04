package org.chocodev.util.Constants;

public enum Acl {
    PUBLIC("public-read"),
    PRIVATE("private");

    private final String value;

    Acl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
