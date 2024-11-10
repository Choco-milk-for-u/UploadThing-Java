package org.chocodev.uploadthing.core.util;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.chocodev.uploadthing.core.api.FileKey;
import org.chocodev.uploadthing.core.api.UTFile;
import org.chocodev.uploadthing.core.api.Exceptions.SDK.FieldException;
import org.chocodev.uploadthing.core.api.Exceptions.SDK.GenerateUrlException;
import org.chocodev.uploadthing.core.internal.Messages;
import org.chocodev.uploadthing.core.internal.ParameterService;
import org.sqids.Sqids;

public class UploadHandler {
    private final String regionAlias;
    private final String apiKey;
    private final String appId;
    private final HmacService HmacService = new HmacService();
    private final HashService HashService = new HashService();
    private final ParameterService ParameterService = new ParameterService();

    public UploadHandler(String apiKey, String appId, String[] regionAlias) {
        this.apiKey = apiKey;
        this.appId = appId;
        this.regionAlias = regionAlias[0];
    }

    private String encodeAppId(String alphabet) {
        Sqids appSqids = Sqids
                .builder()
                .minLength(12)
                .alphabet(alphabet)
                .build();
        Long normalize = Math.abs(Long.valueOf(HashService.stringHash(appId)));
        String encodedApp = appSqids.encode(Arrays.asList(normalize));
        return encodedApp;
    }

    private String encodeFileSeed(String alphabet, UTFile File) {
        List<Serializable> hashParts = List.of(
                File.getName(),
                File.getFileSize(),
                File.getType(),
                File.getLastModified(),
                System.currentTimeMillis());
        String json = Mapper.writeValueAsString(hashParts);
        Sqids fileSqids = Sqids.builder().minLength(36).alphabet(alphabet).build();
        Long normalize = Math.abs(Long.valueOf(HashService.stringHash(json)));
        String encodedFileKey = fileSqids.encode(Arrays.asList(normalize));
        return encodedFileKey;
    }

    public FileKey generateKey(UTFile File) {
        ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), File);
        String alphabet = HashService.shuffle(Sqids.Builder.DEFAULT_ALPHABET, appId);
        return FileKey.builder().setFileKey(encodeAppId(alphabet) + encodeFileSeed(alphabet, File)).build();
    }

    public URI createSignedUploadUrl(String fileKey, Map<String, String> queryParams) {
        try {
            ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), fileKey, queryParams);
            URI preSignedUri = ParameterService.createPreSignedUri(queryParams, regionAlias, fileKey);
            String signature = HmacService.signPayload(preSignedUri.toString(), apiKey);
            String toURL = URLEncoder.encode(signature, StandardCharsets.UTF_8);
            String signedUrl = preSignedUri.toString() + "&signature=" + toURL;
            return new URI(signedUrl);
        } catch (URISyntaxException e) {
            throw new GenerateUrlException();
        }
    }

}
