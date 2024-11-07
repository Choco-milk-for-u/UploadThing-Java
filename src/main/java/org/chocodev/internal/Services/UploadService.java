package org.chocodev.internal.Services;

import java.io.IOException;
import java.net.URI;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTFile;
import org.chocodev.core.UTResponse;
import org.chocodev.core.UploadParameters;
import org.chocodev.core.Responses.UploadPerRequest;
import org.chocodev.internal.RequestService;
import org.chocodev.util.Mapper;
import org.chocodev.util.UploadHandler;
import org.chocodev.util.Decoder.DecodedToken;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadService {
    private final OkHttpClient Client;
    private final UploadHandler UploadHandler;

    public UploadService(OkHttpClient Client, DecodedToken decodedToken) {
        this.Client = Client;
        this.UploadHandler = new UploadHandler(decodedToken.getAppKey(), decodedToken.getAppId(),
                decodedToken.getAppRegion());

    }

    private <TServerData> UTResponse<UploadPerRequest<TServerData>> fetch(Request request, OkHttpClient Client) {
        try {
            Response response = RequestService.executeWithException(request, Client);
            UploadPerRequest<TServerData> Upload = Mapper.readValue(response.body().string(), UploadPerRequest.class);
            response.close();
            return new UTResponse<UploadPerRequest<TServerData>>(response, Upload);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new UTResponse<UploadPerRequest<TServerData>>(e);
        }
    }

    public <TServerData> UTResponse<UploadPerRequest<TServerData>> request(UTFile File, UploadParameters Parameters) {
        FileKey FileKey = UploadHandler.generateKey(File);
        URI URI = UploadHandler.createSignedUploadUrl(FileKey.getFileKey().get(0), Parameters.getQuery());
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", File.getName(), RequestBody.create(File.getBytes()))
                .build();
        Request request = new Request.Builder()
                .url(URI.toString())
                .put(requestBody)
                .build();
        return fetch(request, Client);
    }

}
