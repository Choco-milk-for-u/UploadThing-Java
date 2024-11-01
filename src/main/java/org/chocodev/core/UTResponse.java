package org.chocodev.core;

import java.net.http.HttpResponse;
import java.util.function.Function;

import org.chocodev.internal.Exceptions.SDK.BadApiCallException;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.Constants.Messages;

public class UTResponse<TRes> {
    private boolean isError = false;
    private boolean isOk = false;
    private int status = -1;
    private TRes body = null;
    private Exception exception = null;

    public UTResponse(HttpResponse<String> response, TRes Body) {
        ParametersValidator.validate(new BadApiCallException(Messages.badUTResponseConstructor), Body, response);
        this.status = response.statusCode();
        this.isOk = response.statusCode() == 200;
        this.body = Body;
    }

    public UTResponse(Exception e) {
        ParametersValidator.validate(new BadApiCallException(Messages.badUTResponseConstructor), e);
        this.isError = true;
        this.exception = e;
    }

    public boolean hasException() {
        return isError;
    }

    public TRes getBody() {
        return body;
    }

    public boolean isOk() {
        return isOk;
    }

    public int getStatus() {
        return status;
    }

    public void throwIfException() throws Exception {
        if (exception == null)
            return;

        throw exception;
    }

    public void throwIfException(Function<String, Exception> throwable) throws Exception {
        if (exception == null)
            return;
        throw throwable.apply(exception.getMessage());
    }

}
