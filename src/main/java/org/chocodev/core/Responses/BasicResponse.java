package org.chocodev.core.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicResponse {
    private boolean success;

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
