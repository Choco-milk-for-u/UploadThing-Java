package org.chocodev.util.Decoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.chocodev.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.internal.Messages;
import org.chocodev.util.Mapper;
import org.chocodev.util.ParametersValidator;

public class UploadThingTokenDecoder {

    private String base64ToJson(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);
        return decodedJson;
    }

    public DecodedToken decodedToken(String token) {
        String json = base64ToJson(token);
        DecodedToken DecodedToken = Mapper.readValue(json, DecodedToken.class);
        ParametersValidator.validate(new BadApiCallException(Messages.INVALID_TOKEN), DecodedToken);
        return DecodedToken;
    }
}
