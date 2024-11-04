package org.chocodev.internal.Services;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTFile;
import org.chocodev.core.UTResponse;
import org.chocodev.core.UploadParameters;
import org.chocodev.core.Responses.UploadResponse;
import org.chocodev.util.Mapper;
import org.chocodev.util.UploadHandler;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadService<TServerData> implements IService<ArrayList<UTResponse<UploadResponse<TServerData>>>> {
    private final UTFile[] File;
    private final UploadHandler UploadHandler;
    private final UploadParameters Parameters;
    private int count = 0;

    public UploadService(UploadHandler UploadHandler, UploadParameters Parameters, UTFile... File) {
        this.File = File;
        this.Parameters = Parameters;
        this.UploadHandler = UploadHandler;
    }

    @Override
    public UTResponse<ArrayList<UTResponse<UploadResponse<TServerData>>>> request(OkHttpClient Client) {
        ArrayList<UTResponse<UploadResponse<TServerData>>> responses = new ArrayList<>();
        for (UTFile SingleFile : File) {
            UTResponse<UploadResponse<TServerData>> Response = sendUpload(SingleFile, Client);
            if (Response.isOk()) {
                Response.getBody().setUploadCount(count);
                this.count += 1;
            }
            responses.add(Response);
        }
        return new UTResponse<ArrayList<UTResponse<UploadResponse<TServerData>>>>(true, responses);
    }

    private UTResponse<UploadResponse<TServerData>> sendUpload(UTFile File, OkHttpClient Client) {
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

    private UTResponse<UploadResponse<TServerData>> fetch(Request request, OkHttpClient Client) {
        try {
            System.out.println(request);
            Response response = Client.newCall(request).execute();
            UploadResponse<TServerData> Upload = Mapper.readValue(response.body().string(), UploadResponse.class);
            return new UTResponse<UploadResponse<TServerData>>(response, Upload);
        } catch (IOException e) {
            return new UTResponse<UploadResponse<TServerData>>(e);
        }
    }

}
