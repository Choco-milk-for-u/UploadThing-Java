package org.chocodev.util.Decoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.uploadthing.core.util.Decoder.UploadThingTokenDecoder;
import org.junit.jupiter.api.Test;

public class UploadThingTokenDecoderTest {
    private final UploadThingTokenDecoder decoder = new UploadThingTokenDecoder();

    @Test
    public void decodedTokenWithoutField() throws UnsupportedEncodingException {
        String json = "{\"appId\": \"appId\", \"apiKey\": \"\", \"regions\":[\"regions\"]}";
        // Encode JSON string to Base64
        String base64Encoded = Base64.getEncoder().encodeToString(json.getBytes("UTF-8"));
        assertThrows(BadApiCallException.class, () -> decoder.decodedToken(base64Encoded));
    }
}
