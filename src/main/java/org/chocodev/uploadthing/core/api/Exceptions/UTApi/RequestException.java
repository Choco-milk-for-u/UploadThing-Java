package org.chocodev.uploadthing.core.api.Exceptions.UTApi;

public class RequestException extends UTApiException {
    private int status;

    public int getStatusCode() {
        return this.status;
    }

    public RequestException(String msg) {
        super(msg);
    }

    public RequestException(RequestException exception) {
        this(exception.getMessage(), exception.getStatusCode());
    }

    public RequestException(Exception exception) {
        this(exception.getMessage());
    }

    public RequestException(String body, int statusCode) {
        this(body);
        this.status = statusCode;
    }
}
