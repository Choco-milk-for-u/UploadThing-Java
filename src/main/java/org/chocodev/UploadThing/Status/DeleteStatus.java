package org.chocodev.UploadThing.Status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteStatus extends BasicStatus {
    private int deletedCount;

    @JsonProperty("deletedCount")
    public int getDeletedCount() {
        return deletedCount;
    }

    public void setDeletedCount(int deletedCount) {
        this.deletedCount = deletedCount;
    }
}
