package org.chocodev.UploadThing.Status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicStatus implements IStatus {
    private boolean success;
    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
