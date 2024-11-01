package org.chocodev.internal.Services;

import java.net.http.HttpClient;

import org.chocodev.core.UTResponse;

public interface IService<TResponse> {
    public UTResponse<TResponse> request(HttpClient Client);
}
