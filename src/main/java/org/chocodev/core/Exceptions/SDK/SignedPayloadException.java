package org.chocodev.core.Exceptions.SDK;

import org.chocodev.internal.Messages;

public class SignedPayloadException extends SDKException {
    public SignedPayloadException() {
        super(Messages.SIGN_PAYLOAD_ERROR);
    }
}
