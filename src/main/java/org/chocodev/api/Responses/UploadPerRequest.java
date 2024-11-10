package org.chocodev.api.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadPerRequest<TServerData> {
    @JsonProperty("url")
    private String fileUrl;
    @JsonProperty("appUrl")
    private String appFileUrl;
    @JsonProperty("serverData")
    private TServerData serverData;
    @JsonProperty("fileHash")
    private String fileHash;

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
