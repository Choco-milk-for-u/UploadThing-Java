package org.chocodev.uploadthing.core.api.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteResponse extends BasicResponse {
    @JsonProperty("deletedCount")
    private int deletedCount;
    
    public int getDeletedCount() {
        return deletedCount;
    }
}
