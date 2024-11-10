package org.chocodev.uploadthing.core.util.Decoder;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

/**
 * Decode UploadThing token for use in operations.
 * For more information, see the {@link https://docs.uploadthing.com/v7}.
 */
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

    /**
    * @return string app id of the users token
    */
    public String getAppId() {
        return appId;
    }
    /**
    * @return string app key of the users token
    */
    public String getAppKey() {
        return apiKey;
    }
    /**
    * @return string array that contains regions of the users token
    */
    public String[] getAppRegion() {
        return regions;
    }
}
