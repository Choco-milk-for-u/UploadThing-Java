package org.chocodev.uploadthing.core.util.Constants;

/**
 * ContentDisposition Enum.
 * For more information, see the {@link https://docs.uploadthing.com/api-reference/openapi-spec}.
 */
public enum ContentDisposition {
    /**
    * @return attachment
    */
    ATTACHMENT("attachment"),
    /**
    * @return inline
    */
    INLINE("inline");

    private final String value;

    ContentDisposition(String value) {
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
