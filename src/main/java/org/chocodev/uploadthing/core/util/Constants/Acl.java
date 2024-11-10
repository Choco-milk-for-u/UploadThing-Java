package org.chocodev.uploadthing.core.util.Constants;

/**
 * ACL Enum.
 * For more information, see the {@link https://docs.uploadthing.com/api-reference/openapi-spec}.
 */
public enum Acl {
    /**
    * @return public-read
    */
    PUBLIC("public-read"),
    /**
    * @return private
    */
    PRIVATE("private");

    private final String value;

    Acl(String value) {
        this.value = value;
    }
    /**
     * get value from constants.
     * @return string value.
     */
    public String getValue() {
        return value;
    }
}
