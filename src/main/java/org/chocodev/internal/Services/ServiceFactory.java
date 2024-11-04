package org.chocodev.internal.Services;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTFile;
import org.chocodev.core.UploadParameters;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.util.UploadHandler;

public class ServiceFactory {
    private final String apiKey;

    public ServiceFactory(String apiKey) {
        this.apiKey = apiKey;
    }

    public DeleteService getDeleteService(FileKey File, DeleteOptions Options) {
        return new DeleteService(File, apiKey, Options);
    }

    public <TServerData> UploadService<TServerData> getUploadService(UploadHandler UploadHandler,
            UploadParameters Parameters,
            UTFile... File) {
        return new UploadService<TServerData>(UploadHandler, Parameters, File);
    }
}
