package org.chocodev.util.Decoder;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class DecodedToken {
    @NotEmpty
    @JsonProperty("appId")

    private String appId;
    @NotEmpty

    @JsonProperty("regions")
    private String[] regions;

    @NotEmpty
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
