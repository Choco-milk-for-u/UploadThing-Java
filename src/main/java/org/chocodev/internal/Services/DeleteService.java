package org.chocodev.internal.Services;

import java.io.IOException;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTResponse;
import org.chocodev.core.Options.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.RequestService;
import org.chocodev.internal.UTApiConfig;
import org.chocodev.util.Mapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeleteService {
    private final String apiKey;
    private final OkHttpClient Client;

    public DeleteService(OkHttpClient Client, String apiKey) {
        this.apiKey = apiKey;
        this.Client = Client;
    }
    public UTResponse<DeleteResponse> request(FileKey File, DeleteOptions Options) {
        Request request = new Request.Builder().url(UTApiConfig.deleteFileUrl)
                .header("X-Uploadthing-Api-Key", apiKey)
                .post(RequestBody.create(Options.getRequestBody(File),
                        MediaType.parse("application/json; charset=utf-8")))
                .build();
        return sendRequest(request, Client);
    }

    private UTResponse<DeleteResponse> sendRequest(Request request, OkHttpClient Client) {
        try {
            Response response = RequestService.executeWithException(request, Client);
            DeleteResponse DeleteResponse = Mapper.readValue(response.body().string(), DeleteResponse.class);
            response.close();
            return new UTResponse<DeleteResponse>(response, DeleteResponse);
        } catch (IOException e) {
            return new UTResponse<DeleteResponse>(e);
        }
    }
}
