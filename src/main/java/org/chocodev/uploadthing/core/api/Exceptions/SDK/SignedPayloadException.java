package org.chocodev.uploadthing.core.api.Exceptions.SDK;

import org.chocodev.uploadthing.core.internal.Messages;

public class SignedPayloadException extends SDKException {
    public SignedPayloadException() {
        super(Messages.SIGN_PAYLOAD_ERROR);
    }
}
