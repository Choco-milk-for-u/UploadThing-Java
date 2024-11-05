package org.chocodev.util;

import org.chocodev.core.UploadParameters;
import org.chocodev.core.Exceptions.SDK.BadApiCallException;
import org.chocodev.internal.Messages;
import org.chocodev.util.Constants.Acl;
import org.chocodev.util.Constants.ContentDisposition;

public class UploadParametersBuilder extends UploadParameters {

    private UploadParametersBuilder() {
        super();
    }

    public UploadParametersBuilder setExpire(String exp) {
        this.query.put("expires", exp);
        return this;
    }

    public UploadParametersBuilder setAppId(String appId) {
        this.query.put("x-ut-identifier", appId);
        return this;
    }

    public UploadParametersBuilder setFileName(String fileName) {
        this.query.put("x-ut-file-name", fileName);
        return this;
    }

    public UploadParametersBuilder setFileSize(long fileSize) {
        this.query.put("x-ut-file-size", String.valueOf(fileSize));
        return this;
    }

    public UploadParametersBuilder setFileType(String fileType) {
        this.query.put("x-ut-file-type", fileType);
        return this;
    }

    public UploadParametersBuilder setCustomId(String customId) {
        this.query.put("x-ut-custom-id", customId);
        return this;

    }

    public UploadParametersBuilder setContentDisposition(ContentDisposition contentDisposition) {
        this.query.put("x-ut-content-disposition", contentDisposition.getValue());
        return this;

    }

    UploadParametersBuilder setACL(Acl acl) {
        this.query.put("x-ut-acl", acl.getValue());
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
