package org.chocodev.core;

import org.chocodev.core.Exceptions.SDK.FieldException;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.core.Responses.UploadResponse;
import org.chocodev.internal.Messages;
import org.chocodev.internal.Services.ServiceFactory;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.UploadHandler;
import org.chocodev.util.UploadHandlerBuilder;
import org.chocodev.util.Decoder.DecodedToken;
import org.chocodev.util.Decoder.UploadThingTokenDecoder;

import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.ArrayList;

public class UTApi {
    private final OkHttpClient Client = new OkHttpClient();

    private final ServiceFactory ServiceFactory;
    private final UploadHandler UploadHandler;
    private final DecodedToken decodedToken;

    public UTApi(String token) {
        UploadThingTokenDecoder decoder = new UploadThingTokenDecoder();
        this.decodedToken = decoder.decodedToken(token);
        this.ServiceFactory = new ServiceFactory(decodedToken.getAppKey());
        this.UploadHandler = new UploadHandler(decodedToken.getAppKey(), decodedToken.getAppId(),
                decodedToken.getAppRegion());
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File, DeleteOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), File, Options);
        return ServiceFactory.getDeleteService(File, Options).request(Client);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File) {
        return deleteFiles(File, DeleteOptions.withDefault());
    }

    public <TServerData> UTResponse<ArrayList<UTResponse<UploadResponse<TServerData>>>> uploadFiles(
            UploadParameters Parameters, UTFile... File) throws IOException, InterruptedException {
        return ServiceFactory.<TServerData>getUploadService(UploadHandler, Parameters, File).request(Client);
    }

    public <TServerData> UTResponse<ArrayList<UTResponse<UploadResponse<TServerData>>>> uploadFiles(UTFile File)
            throws IOException, InterruptedException {
        UploadParameters Parameters = UploadHandlerBuilder
                .builder(File.getName(), File.getFileSize(), decodedToken.getAppId())
                .build();
        return uploadFiles(Parameters, File);
    }
}
