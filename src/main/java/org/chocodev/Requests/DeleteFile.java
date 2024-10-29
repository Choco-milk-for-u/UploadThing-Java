package org.chocodev.Requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;

import org.chocodev.UTApiConfig;
import org.chocodev.Error.SDK.MapperException;
import org.chocodev.Error.UTApi.RequestException;
import org.chocodev.Fetch.IFetch;
import org.chocodev.UploadThing.Constants.Messages;
import org.chocodev.UploadThing.Customizer.DeleteCustomizer.DeleteOptions;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.Status.DeleteStatus;
import org.chocodev.UploadThing.Status.IStatus;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DeleteFile implements IFetch<IStatus> {
    private final HttpClient Client;
    private final DeleteOptions Options;
    private final String utApiToken;
    private final FileKeys Files;

    public DeleteFile(FileKeys Files, String utApiToken, DeleteOptions Options, HttpClient Client) {
        this.Client = Client;
        this.Options = Options;
        this.utApiToken = utApiToken;
        this.Files = Files;
    }

    private DeleteStatus sendRequest(HttpRequest request) throws RequestException {
        try {
            HttpResponse<String> response = Client.send(request, HttpResponse.BodyHandlers.ofString());
            handleResponseStatus(response);
            return RequestMapper.objectMapper.readValue(response.body(), DeleteStatus.class);
        } catch (JsonProcessingException e) {
            throw new MapperException(Messages.mapperErrorMessage);
        } catch (IOException | InterruptedException e) {
            throw new RequestException(e);
        } 
    }

    private void handleResponseStatus(HttpResponse<String> response) throws RequestException {
        if (response.statusCode() != 200) {
            throw new RequestException(response.body(), response.statusCode());
        }
    }

    @Override
    public IStatus request() throws RequestException {
        Builder http = HttpRequest.newBuilder()
                .uri(URI.create(UTApiConfig.deleteFileUrl))
                .header("Content-Type", "application/json")
                .header("X-Uploadthing-Api-Key", utApiToken);
        http.method("POST", HttpRequest.BodyPublishers.ofString(Options.getJson(Files)));
        HttpRequest request = http.build();
        return sendRequest(request);
    }

}
