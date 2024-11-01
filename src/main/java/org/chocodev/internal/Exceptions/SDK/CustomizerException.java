package org.chocodev.internal.Exceptions.SDK;

import org.chocodev.util.Constants.Messages;

public class CustomizerException extends SDKException {
    public CustomizerException(){
        super(Messages.customizerErrorMessage);
    }
    public CustomizerException(String msg) {
        super(msg);
    }
    
}
