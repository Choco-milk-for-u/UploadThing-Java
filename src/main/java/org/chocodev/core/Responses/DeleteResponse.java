package org.chocodev.core.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteResponse extends BasicResponse {
    @JsonProperty("deletedCount")
    private int deletedCount;
    
    public int getDeletedCount() {
        return deletedCount;
    }
}
