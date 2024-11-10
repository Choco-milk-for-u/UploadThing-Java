package org.chocodev.uploadthing.core.util.Constants;

public enum QueryParameters {
    EXPIRES("expires"),
    ID("x-ut-identifier"),
    FILE_NAME("x-ut-file-name"),
    FILE_SIZE("x-ut-file-size"),
    FILE_TYPE("x-ut-file-type"),
    CUSTOM_ID("x-ut-custom-id"),
    CONTENT_DISPOSITION("x-ut-content-disposition"),
    ACL("x-ut-acl");

    private final String value;

    QueryParameters(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
