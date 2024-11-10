package org.chocodev.uploadthing.core.internal.Services;

import org.chocodev.uploadthing.core.util.Decoder.DecodedToken;

import okhttp3.OkHttpClient;

public class Services {
    public final UploadService uploadService;
    public final DeleteService deleteService;
    private final OkHttpClient Client = new OkHttpClient();

    public Services(DecodedToken decodedToken) {
        uploadService = new UploadService(Client, decodedToken);
        deleteService = new DeleteService(Client, decodedToken.getAppKey());
    }
}
