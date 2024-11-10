package org.chocodev.uploadthing.core.api;

import java.util.function.Function;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.uploadthing.core.internal.Messages;
import org.chocodev.uploadthing.core.util.ParametersValidator;

import okhttp3.Response;

public class UTResponse<TRes> {
    private boolean isError = false;
    private boolean isOk = false;
    private int status = -1;
    private TRes body = null;
    private Exception exception = null;

    public UTResponse(Response response, TRes Body) {
        ParametersValidator.validate(new BadApiCallException(Messages.BAD_UT_RESPONSE_CONSTRUCTOR), Body, response);
        this.status = response.code();
        this.isOk = response.isSuccessful();
        this.body = Body;
    }
    public UTResponse(boolean isOk, TRes Body){
        this.isOk = isOk;
        this.body = Body;
    }

    public UTResponse(Exception e) {
        ParametersValidator.validate(new BadApiCallException(Messages.BAD_UT_RESPONSE_CONSTRUCTOR), e);
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
