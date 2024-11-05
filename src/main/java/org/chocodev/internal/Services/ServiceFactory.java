package org.chocodev.internal.Services;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTFile;
import org.chocodev.core.UploadParameters;
import org.chocodev.core.Exceptions.SDK.FieldException;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.internal.Messages;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.UploadHandler;

public class ServiceFactory {
    private final String apiKey;

    public ServiceFactory(String apiKey) {
        this.apiKey = apiKey;
    }

    public DeleteService getDeleteService(FileKey File, DeleteOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), File, Options);
        return new DeleteService(File, apiKey, Options);
    }

    public <TServerData> UploadService<TServerData> getUploadService(UploadHandler UploadHandler,
            UploadParameters Parameters,
            UTFile... File) {
        ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), File, Parameters, UploadHandler);
        return new UploadService<TServerData>(UploadHandler, Parameters, File);
    }
}
