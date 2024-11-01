package org.chocodev.core;

import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.Exceptions.SDK.FieldException;
import org.chocodev.internal.Services.ServiceFactory;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.Constants.Messages;

import java.net.http.HttpClient;

public class UTApi {
    private final ServiceFactory ServiceFactory;
    private final HttpClient Client = HttpClient.newHttpClient();
    private final String utApiToken;

    public UTApi(String token) {
        this.utApiToken = token;
        this.ServiceFactory = new ServiceFactory(utApiToken);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File, DeleteOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), File, Options);
        return ServiceFactory.getDeleteService(File, Options).request(Client);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File) {
        return deleteFiles(File, DeleteOptions.withDefault());
    }
}
