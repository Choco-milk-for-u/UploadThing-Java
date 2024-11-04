package org.chocodev.core;

import org.chocodev.core.Exceptions.SDK.FieldException;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.Messages;
import org.chocodev.internal.Services.ServiceFactory;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.UploadHandler;
import org.chocodev.util.UploadHandlerBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class UTApi {
    private final ServiceFactory ServiceFactory;
    private final HttpClient Client = HttpClient.newHttpClient();
    private final String utApiToken;
    private final UploadHandler UploadHandler;

    public UTApi(String token) {
        this.utApiToken = token;
        this.ServiceFactory = new ServiceFactory(utApiToken);
        this.UploadHandler = new UploadHandler(utApiToken, "ud3lmv47if");
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File, DeleteOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), File, Options);
        return ServiceFactory.getDeleteService(File, Options).request(Client);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File) {
        return deleteFiles(File, DeleteOptions.withDefault());
    }

    public void uploadFiles(UTFile File) throws IOException, InterruptedException {
        FileKey FileKey = UploadHandler.generateKey(File.getName());
        Map<String, String> query = UploadHandlerBuilder
                .builder()
                .setFileName(File.getName())
                .setFileSize(File.getFileSize())
                .setExpire(String.valueOf(System.currentTimeMillis() / 1000 + 3600))
                .setAppId("ud3lmv47if")
                .build();
        URI URI = UploadHandler.createSignedUploadUrl(FileKey.getFileKey().get(0), query);
        System.out.println(URI);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI)
                .header("Content-Type", "multipart/form-data")
                .header("X-Uploadthing-Api-Key", utApiToken)
                .method("PUT", HttpRequest.BodyPublishers.ofByteArray(File.getBytes()))
                .build();
        HttpResponse<String> response = Client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
