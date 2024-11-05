package org.chocodev.util.Decoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.chocodev.core.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

public class UploadThingTokenDecoderTest {
    private final UploadThingTokenDecoder decoder = new UploadThingTokenDecoder();

    @Test
    public void decodedTokenWithoutField() throws UnsupportedEncodingException {
        String json = "{\"appId\": \"appId\"}";
        // Encode JSON string to Base64
        String base64Encoded = Base64.getEncoder().encodeToString(json.getBytes("UTF-8"));
        assertThrows(BadApiCallException.class, () -> decoder.decodedToken(base64Encoded));
    }
}