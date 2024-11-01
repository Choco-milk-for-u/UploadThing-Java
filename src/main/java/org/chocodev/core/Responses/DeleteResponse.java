package org.chocodev.core.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteResponse extends BasicResponse {
    private int deletedCount;
    
    @JsonProperty("deletedCount")
    public int getDeletedCount() {
        return deletedCount;
    }

    public void setDeletedCount(int deletedCount) {
        this.deletedCount = deletedCount;
    }
}
