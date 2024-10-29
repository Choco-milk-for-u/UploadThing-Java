package org.chocodev.Error.SDK;

import org.chocodev.UploadThing.Constants.Messages;

public class CustomizerException extends SDKException {
    public CustomizerException(){
        super(Messages.customizerErrorMessage);
    }
    public CustomizerException(String msg) {
        super(msg);
    }
    
}
