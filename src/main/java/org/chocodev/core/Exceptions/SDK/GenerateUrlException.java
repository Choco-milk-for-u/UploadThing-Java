package org.chocodev.core.Exceptions.SDK;

import org.chocodev.internal.Messages;

public class GenerateUrlException extends SDKException {
    public GenerateUrlException() {
        super(Messages.generateUrlErrorMessage);
    }
}
