package org.chocodev.internal.Services;

import org.chocodev.core.UTResponse;

import okhttp3.OkHttpClient;

public interface IService<TResponse> {
    public UTResponse<TResponse> request(OkHttpClient Client);
}
