package org.chocodev.uploadthing.core.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ParameterService {
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

    public URI createPreSignedUri(Map<String, String> queryParams, String regionAlias, String fileKey)
            throws URISyntaxException {
        String baseUrl = String.format(UTApiConfig.uploadFileUrl, regionAlias, fileKey);
        StringBuilder queryString = applyParameters(queryParams);
        String combineQuery = baseUrl + (queryString.length() > 0 ? "?" + queryString.toString() : "");
        URI preSignedUri = new URI(combineQuery);
        return preSignedUri;
    }
}
