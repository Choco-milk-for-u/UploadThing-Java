package org.chocodev.internal.Services;

import org.chocodev.core.FileKey;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;

public class ServiceFactory {
    private final String token;
    public ServiceFactory(String token){
        this.token = token;
    }
    public DeleteService getDeleteService(FileKey File, DeleteOptions Options) {
        return new DeleteService(File, token, Options);
    }
}
