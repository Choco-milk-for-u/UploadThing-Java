package org.chocodev.Error;

public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);
    }
}
