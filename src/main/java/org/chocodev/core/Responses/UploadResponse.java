package org.chocodev.core.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadResponse<TServerData> {
    @JsonProperty("url")
    private String fileUrl;
    @JsonProperty("appUrl")
    private String appFileUrl;
    @JsonProperty("serverData")
    private TServerData serverData;
    @JsonProperty("fileHash")
    private String fileHash;
    private int uploadCount;

    public void setUploadCount(int number) {
        this.uploadCount = number;
    }

    public int getUploadCount() {
        return uploadCount;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getAppFileUrl() {
        return appFileUrl;
    }

    public String getFileHash() {
        return fileHash;
    }

    public TServerData getServerData() {
        return serverData;
    }
}
