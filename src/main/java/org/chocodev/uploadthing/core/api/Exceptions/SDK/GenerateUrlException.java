package org.chocodev.uploadthing.core.api.Exceptions.SDK;

import org.chocodev.uploadthing.core.internal.Messages;

public class GenerateUrlException extends SDKException {
    public GenerateUrlException() {
        super(Messages.GENERATE_URL_ERROR_MESSAGE);
    }
}
