package org.chocodev.Fetch;

import org.chocodev.Error.RequestException;

public interface IFetch<Response> {
    public Response request() throws RequestException;
}
