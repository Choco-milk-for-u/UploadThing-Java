package org.chocodev.core;

import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.Services.DeleteService;

import java.net.http.HttpClient;

public class UTApi {
    private final HttpClient Client = HttpClient.newHttpClient();
    private final String utApiToken;

    public UTApi(String token) {
        this.utApiToken = token;
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File, DeleteOptions Options) {
        return new DeleteService(File, utApiToken, Options).request(Client);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File) {
        return deleteFiles(File, DeleteOptions.withDefault());
    }
}
