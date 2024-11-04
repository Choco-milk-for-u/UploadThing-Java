package org.chocodev.util.Decoder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodedToken {
    @JsonProperty("appId")
    private String appId;

    @JsonProperty("regions")
    private String[] regions;

    @JsonProperty("apiKey")
    private String apiKey;

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return apiKey;
    }

    public String[] getAppRegion() {
        return regions;
    }
}
