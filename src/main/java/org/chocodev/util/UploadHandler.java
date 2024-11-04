package org.chocodev.util;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import org.chocodev.core.FileKey;
import org.chocodev.core.Exceptions.SDK.GenerateUrlException;
import org.chocodev.internal.UTApiConfig;
import org.sqids.Sqids;

public class UploadHandler {
    private String regionAlias = "sea1";
    private final String apiKey;
    private final String appId;
    private final HmacService HmacService = new HmacService();

    public UploadHandler(String regionAlias, String apiKey, String appId) {
        this(apiKey, appId);
        this.regionAlias = regionAlias;
    }

    public UploadHandler(String apiKey, String appId) {
        this.apiKey = apiKey;
        this.appId = appId;
    }

    public FileKey generateKey(String fileSeed) {
        Sqids sqids = Sqids.builder()
                .minLength(12)
                .build();
        Long normalized = Math.abs(Long.valueOf(appId.hashCode()));
        String encodedApp = sqids.encode(Arrays.asList(normalized));
        String encodedFileKey = Base64.getUrlEncoder().encodeToString(fileSeed.getBytes());
        return FileKey.builder().setFileKey(encodedApp + encodedFileKey).build();
    }

    public URI createSignedUploadUrl(String fileKey, Map<String, String> queryParams) {
        String baseUrl = String.format(UTApiConfig.uploadFileUrl, regionAlias, fileKey);
        try {
            return HmacService.buildSignedUrl(baseUrl, queryParams, apiKey);
        } catch (Exception e) {
            throw new GenerateUrlException();
        }
    }

}
