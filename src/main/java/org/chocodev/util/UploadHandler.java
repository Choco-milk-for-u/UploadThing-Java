package org.chocodev.util;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.chocodev.core.FileKey;
import org.chocodev.core.Exceptions.SDK.GenerateUrlException;
import org.chocodev.internal.UTApiConfig;
import org.sqids.Sqids;

public class UploadHandler {
    private String regionAlias = "sea1";
    private final String apiKey;
    private final String appId;

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

    private URI buildSignedUrl(String baseUrl, Map<String, String> queryParams)
            throws Exception {
        StringBuilder queryString = new StringBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            queryString.append("=");
            queryString.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        String preSignedUrl = baseUrl + (queryString.length() > 0 ? "?" + queryString.toString() : "");
        URI preSignedUri = new URI(preSignedUrl);
        String signature = generateHmacSha256Signature(preSignedUri.toString());
        String signedUrl = preSignedUri.toString() + "&signature="
                + URLEncoder.encode(signature, StandardCharsets.UTF_8);

        return new URI(signedUrl);
    }

    private String generateHmacSha256Signature(String data) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(apiKey.getBytes("UTF-8"), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] signedBytes = sha256Hmac.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    public URI createSignedUploadUrl(String fileKey, Map<String, String> queryParams) {
        String baseUrl = String.format(UTApiConfig.uploadFileUrl, regionAlias, fileKey);
        try {
            return buildSignedUrl(baseUrl, queryParams);
        } catch (Exception e) {
            throw new GenerateUrlException();
        }
    }

}
