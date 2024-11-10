package org.chocodev.uploadthing.core.api.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class to be extended.
 * every response of UTApi is a subclass of BasicResponse class.
 */
public class BasicResponse {
    private boolean success;

    /**
     * if operation was success.
     * @return boolean
     */
    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }
}
