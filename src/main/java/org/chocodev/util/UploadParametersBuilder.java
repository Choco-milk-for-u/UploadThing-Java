package org.chocodev.util;

import org.chocodev.api.UploadParameters;
import org.chocodev.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.internal.Messages;
import org.chocodev.util.Constants.Acl;
import org.chocodev.util.Constants.ContentDisposition;
import org.chocodev.util.Constants.QueryParameters;

public class UploadParametersBuilder extends UploadParameters {

    private UploadParametersBuilder() {
        super();
    }

    public UploadParametersBuilder setExpire(String exp) {
        this.query.put(QueryParameters.EXPIRES.getValue(), exp);
        return this;
    }

    public UploadParametersBuilder setAppId(String appId) {
        this.query.put(QueryParameters.ID.getValue(), appId);
        return this;
    }

    public UploadParametersBuilder setFileName(String fileName) {
        this.query.put(QueryParameters.FILE_NAME.getValue(), fileName);
        return this;
    }

    public UploadParametersBuilder setFileSize(long fileSize) {
        this.query.put(QueryParameters.FILE_SIZE.getValue(), String.valueOf(fileSize));
        return this;
    }

    public UploadParametersBuilder setFileType(String fileType) {
        this.query.put(QueryParameters.FILE_TYPE.getValue(), fileType);
        return this;
    }

    public UploadParametersBuilder setCustomId(String customId) {
        this.query.put(QueryParameters.CUSTOM_ID.getValue(), customId);
        return this;

    }

    public UploadParametersBuilder setContentDisposition(ContentDisposition contentDisposition) {
        this.query.put(QueryParameters.CONTENT_DISPOSITION.getValue(), contentDisposition.getValue());
        return this;
    }

    public UploadParametersBuilder setACL(Acl acl) {
        this.query.put(QueryParameters.ACL.getValue(), acl.getValue());
        return this;

    }

    public static UploadParametersBuilder builder(String fileName, long fileSize, String appId) {
        ParametersValidator.validate(new BadApiCallException(Messages.BAD_BUILDER_BUILD), fileName, fileSize, appId);
        UploadParametersBuilder builder = new UploadParametersBuilder()
                .setAppId(appId)
                .setFileName(fileName)
                .setFileSize(fileSize)
                .setExpire(String.valueOf(System.currentTimeMillis() + 60 * 60 * 1000));
        return builder;
    }

    public UploadParameters build() {
        return this.UploadParameters;
    }

}
