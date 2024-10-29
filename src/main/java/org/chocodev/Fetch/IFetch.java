package org.chocodev.Fetch;

import org.chocodev.Error.UTApi.RequestException;

public interface IFetch<Response> {
    public Response request() throws RequestException;
}
