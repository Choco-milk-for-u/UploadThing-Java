package org.chocodev.core.Exceptions.SDK;

import org.chocodev.internal.Messages;

public class CustomizerException extends SDKException {
    public CustomizerException(){
        super(Messages.customizerErrorMessage);
    }
    public CustomizerException(String msg) {
        super(msg);
    }
    
}
