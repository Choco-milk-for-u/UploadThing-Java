package org.chocodev.uploadthing.core.api.Exceptions.SDK;

/**
 * Exception representing bad call of public sdk.
 */
public class BadApiCallException extends SDKException {

    /**
     * @param msg of the exception.
     */

    public BadApiCallException(String msg) {
        super(msg);
    }

}
