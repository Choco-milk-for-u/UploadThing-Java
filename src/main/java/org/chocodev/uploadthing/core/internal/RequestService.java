package org.chocodev.uploadthing.core.internal;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestService {
     public static Response executeWithException(Request request, OkHttpClient Client) throws IOException {
        Response response = Client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code: " + response.code() + " " + response.body().string());
        }
        return response;
    }
}
