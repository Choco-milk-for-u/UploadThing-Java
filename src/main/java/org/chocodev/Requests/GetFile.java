package org.chocodev.Requests;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.chocodev.UTApiConfig;
import org.chocodev.Error.UTApi.RequestException;
import org.chocodev.Fetch.IFetch;
import org.chocodev.UploadThing.Constants.Messages;
import org.chocodev.UploadThing.File.FileData;
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
        try {
            HttpResponse<byte[]> response = Client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            handleResponseStatus(response);
            return response;
        } catch (RequestException e) {
            throw new RequestException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new RequestException(Messages.requestErrorMessage(e.getMessage()));
        }
    }

    private void handleResponseStatus(HttpResponse<byte[]> response) throws RequestException {
        if (response.statusCode() != 200) {
            throw new RequestException(response.toString(), response.statusCode());
        }
    }

    @Override
    public FileData request() throws RequestException {
        HttpResponse<byte[]> response = sendRequest();
        byte[] responseBytes = response.body();
        String contentType = response.headers().firstValue("Content-Type").orElse("unknown");
        String contentDisposition = response.headers().firstValue("Content-Disposition").orElse("unknown");
        return new FileData(extractFileName(contentDisposition), contentType, responseBytes);

    }

}
