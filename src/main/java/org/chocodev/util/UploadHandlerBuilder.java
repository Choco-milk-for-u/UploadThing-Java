package org.chocodev.util;

import org.chocodev.core.UploadParameters;
import org.chocodev.util.Constants.Acl;
import org.chocodev.util.Constants.ContentDisposition;

public class UploadHandlerBuilder extends UploadParameters {

    private UploadHandlerBuilder() {
        super();
    }

    public UploadHandlerBuilder setExpire(String exp) {
        this.query.put("expires", exp);
        return this;
    }

    public UploadHandlerBuilder setAppId(String appId) {
        this.query.put("x-ut-identifier", appId);
        return this;
    }

    public UploadHandlerBuilder setFileName(String fileName) {
        this.query.put("x-ut-file-name", fileName);
        return this;
    }

    public UploadHandlerBuilder setFileSize(long fileSize) {
        this.query.put("x-ut-file-size", String.valueOf(fileSize));
        return this;
    }

    public UploadHandlerBuilder setFileType(String fileType) {
        this.query.put("x-ut-file-type", fileType);
        return this;
    }

    public UploadHandlerBuilder setCustomId(String customId) {
        this.query.put("x-ut-custom-id", customId);
        return this;

    }

    public UploadHandlerBuilder setContentDisposition(ContentDisposition contentDisposition) {
        this.query.put("x-ut-content-disposition", contentDisposition.getValue());
        return this;

    }

    UploadHandlerBuilder setACL(Acl acl) {
        this.query.put("x-ut-acl", acl.getValue());
        return this;

    }

    public static UploadHandlerBuilder builder(String fileName, long fileSize, String appId) {
        UploadHandlerBuilder builder = new UploadHandlerBuilder()
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
