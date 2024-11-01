package org.chocodev.internal.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTResponse;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.UTApiConfig;
import org.chocodev.util.Mapper;

public class DeleteService implements IService<DeleteResponse> {
    private final String token;
    private final FileKey File;
    private final DeleteOptions Options;

    public DeleteService(FileKey File, String utApiToken, DeleteOptions Options) {
        this.token = utApiToken;
        this.File = File;
        this.Options = Options;
    }

    @Override
    public UTResponse<DeleteResponse> request(HttpClient Client) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(UTApiConfig.deleteFileUrl))
                .header("Content-Type", "application/json")
                .header("X-Uploadthing-Api-Key", token)
                .method("POST", HttpRequest.BodyPublishers.ofString(Options.getRequestBody(File)))
                .build();
        return sendRequest(request, Client);
    }

    private UTResponse<DeleteResponse> sendRequest(HttpRequest request, HttpClient Client) {
        try {
            HttpResponse<String> response = Client.send(request, HttpResponse.BodyHandlers.ofString());
            DeleteResponse DeleteResponse = Mapper.readValue(response.body(), DeleteResponse.class);

            return new UTResponse<DeleteResponse>(response, DeleteResponse);
        } catch (IOException | InterruptedException e) {
            return new UTResponse<DeleteResponse>(e);
        }
    }
}
