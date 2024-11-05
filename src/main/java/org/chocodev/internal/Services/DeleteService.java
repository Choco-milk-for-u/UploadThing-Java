package org.chocodev.internal.Services;

import java.io.IOException;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTResponse;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.UTApiConfig;
import org.chocodev.util.Mapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeleteService implements IService<DeleteResponse> {
    private final String apiKey;
    private final FileKey File;
    private final DeleteOptions Options;

    public DeleteService(FileKey File, String utApiToken, DeleteOptions Options) {
        this.apiKey = utApiToken;
        this.File = File;
        this.Options = Options;
    }

    @Override
    public UTResponse<DeleteResponse> request(OkHttpClient Client) {
        Request request = new Request.Builder().url(UTApiConfig.deleteFileUrl)
                .header("X-Uploadthing-Api-Key", apiKey)
                .post(RequestBody.create(Options.getRequestBody(File),
                        MediaType.parse("application/json; charset=utf-8")))
                .build();
        return sendRequest(request, Client);
    }

    private UTResponse<DeleteResponse> sendRequest(Request request, OkHttpClient Client) {
        try {
            Response response = Client.newCall(request).execute();
            DeleteResponse DeleteResponse = Mapper.readValue(response.body().string(), DeleteResponse.class);
            response.close();
            return new UTResponse<DeleteResponse>(response, DeleteResponse);
        } catch (IOException e) {
            return new UTResponse<DeleteResponse>(e);
        }
    }
}
