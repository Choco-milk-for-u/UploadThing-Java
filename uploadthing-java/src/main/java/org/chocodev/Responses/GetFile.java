package org.chocodev.Responses;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.chocodev.UTApiConfig;
import org.chocodev.Error.RequestException;
import org.chocodev.Fetch.IFetch;
import org.chocodev.UploadThing.File.File;
import org.chocodev.UploadThing.File.IFile;

public class GetFile implements IFetch<IFile> {
    private final HttpClient Client;
    private final HttpRequest request;

    public GetFile(HttpClient Client, String url) {
        this.Client = Client;
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(UTApiConfig.accessFileUrl + url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private String extractFileName(String contentDisposition) {
        return contentDisposition.split(UTApiConfig.UTFileName + "=")[1].split(";")[0]
                .replace("\"", "").trim();
    }

    private HttpResponse<byte[]> sendRequest() throws RequestException {
        HttpResponse<byte[]> response;
        try {
            response = Client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException | InterruptedException e) {
            throw new RequestException(UTApiConfig.requestError);
        }
        if (response.statusCode() != 200) {
            throw new RequestException(UTApiConfig.fileReciveError);
        }
        return response;
    }

    @Override
    public File request() throws RequestException {
        HttpResponse<byte[]> response = sendRequest();
        byte[] responseBytes = response.body();
        String contentType = response.headers().firstValue("Content-Type").orElse("unknown");
        String contentDisposition = response.headers().firstValue("Content-Disposition").orElse("unknown");
        return new File(extractFileName(contentDisposition), contentType, responseBytes);

    }

}
