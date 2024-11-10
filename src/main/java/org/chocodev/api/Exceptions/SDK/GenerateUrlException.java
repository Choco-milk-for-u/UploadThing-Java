package org.chocodev.api.Exceptions.SDK;

import org.chocodev.internal.Messages;

public class GenerateUrlException extends SDKException {
    public GenerateUrlException() {
        super(Messages.GENERATE_URL_ERROR_MESSAGE);
    }
}
