package org.chocodev.util;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacService {
    private final String ALG = "HmacSHA256";

    public URI buildSignedUrl(String baseUrl, Map<String, String> queryParams, String apiKey)
            throws Exception {

        StringBuilder queryString = applyParameters(queryParams);
        String preSignedUrl = baseUrl + (queryString.length() > 0 ? "?" + queryString.toString() : "");
        URI preSignedUri = new URI(preSignedUrl);
        String signature = signPayload(preSignedUri.toString(), apiKey);
        String signedUrl = preSignedUri.toString() + "&signature="
                + URLEncoder.encode(signature, StandardCharsets.UTF_8);

        return new URI(signedUrl);
    }

    private StringBuilder applyParameters(Map<String, String> queryParams) {
        StringBuilder queryString = new StringBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            queryString.append("=");
            queryString.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return queryString;
    }

    private String encodeToHex(byte[] hmacBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hmacBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String signPayload(String payload, String secret) throws Exception {
        Mac mac = Mac.getInstance(ALG);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALG);
        mac.init(secretKeySpec);

        byte[] hmacBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        return "hmac-sha256=" + encodeToHex(hmacBytes);
    }
}
