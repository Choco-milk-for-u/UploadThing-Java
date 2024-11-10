package org.chocodev.api;

import org.chocodev.api.Exceptions.SDK.FieldException;
import org.chocodev.api.Options.DeleteOptions;
import org.chocodev.api.Options.UploadOptions;
import org.chocodev.api.Responses.DeleteResponse;
import org.chocodev.api.Responses.UploadPerRequest;
import org.chocodev.api.Responses.UploadResponse;
import org.chocodev.internal.Messages;
import org.chocodev.internal.Mappers.UploadMapper;
import org.chocodev.internal.Services.Services;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.Decoder.DecodedToken;
import org.chocodev.util.Decoder.UploadThingTokenDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * see more at
 * {@link <a href="https://docs.uploadthing.com/api-reference/ut-api">docs</a>}
 */
public class UTApi {
    private final Services services;
    private final DecodedToken decodedToken;

    public UTApi(String token) {
        this.decodedToken = new UploadThingTokenDecoder().decodedToken(token);
        this.services = new Services(decodedToken);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File, DeleteOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), File, Options);
        return services.deleteService.request(File, Options);
    }

    public UTResponse<DeleteResponse> deleteFiles(FileKey File) {
        return deleteFiles(File, DeleteOptions.withDefault());
    }

    public <TServerData> UTResponse<UploadPerRequest<TServerData>> uploadFiles(UTFile File, UploadOptions Options) {
        ParametersValidator.validate(new FieldException(Messages.FIELD_ERROR_MESSAGE), File, Options);
        UploadParameters Parameters = UploadMapper.transform(File, Options, decodedToken.getAppId());
        return services.uploadService.request(File, Parameters);
    }

    public <TServerData> UTResponse<UploadResponse<TServerData>> uploadFiles(Collection<UTFile> Files,
            UploadOptions Options) {
        int uploadCount = 0;
        List<UTResponse<UploadPerRequest<TServerData>>> responses = new ArrayList<>();
        for (UTFile File : Files) {
            UTResponse<UploadPerRequest<TServerData>> Upload = uploadFiles(File, Options);
            if (Upload.isOk()) {
                uploadCount = uploadCount + 1;
            }
            responses.add(Upload);
        }
        UploadResponse<TServerData> UploadResponse = new UploadResponse<TServerData>(responses, uploadCount);
        return new UTResponse<UploadResponse<TServerData>>(true, UploadResponse);
    }

    public <TServerData> UTResponse<UploadResponse<TServerData>> uploadFiles(Collection<UTFile> Files) {
        return uploadFiles(Files, UploadOptions.builder().build());
    }

    public <TServerData> UTResponse<UploadPerRequest<TServerData>> uploadFiles(UTFile File) {
        return uploadFiles(File, UploadOptions.builder().build());
    }
}
