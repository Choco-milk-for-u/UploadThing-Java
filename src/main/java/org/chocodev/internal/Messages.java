package org.chocodev.internal;

public class Messages {
    private Messages() {

    }
    public static final String MAPPER_ERROR_MESSAGE = "Could not transform to JSON or read from JSON";
    public static final String FIELD_ERROR_MESSAGE = "One or more fields are empty or null";
    public static final String BAD_UT_RESPONSE_CONSTRUCTOR = "Could not construct UTResponse due to null data";
    public static final String EMPTY_KEY_ERROR_MESSAGE = "One of the keys passed is either null or empty";
    public static final String BAD_BUILDER_BUILD = "Incorrect builder configuration";
    public static final String GENERATE_URL_ERROR_MESSAGE = "Could not sign payload";
    public static final String INVALID_TOKEN = "The token provided is invalid. Please use a V7 token from UploadThing";
    public static final String SIGN_PAYLOAD_ERROR = "An error occurred with the HMAC service";

}
