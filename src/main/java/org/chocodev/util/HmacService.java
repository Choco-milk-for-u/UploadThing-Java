package org.chocodev.util;

import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.chocodev.core.Exceptions.SDK.SignedPayloadException;

public class HmacService {
    private final String ALG = "HmacSHA256";
    private final String PREFIX = "hmac-sha256=";

    
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

    public String signPayload(String payload, String secret) {
        try {
            Mac mac = Mac.getInstance(ALG);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALG);
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return PREFIX + encodeToHex(hmacBytes);
        } catch (Exception e) {
            throw new SignedPayloadException();
        }
    }
}
