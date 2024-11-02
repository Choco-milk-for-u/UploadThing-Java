package org.chocodev.util;

import java.util.HashMap;
import java.util.Map;

import org.chocodev.util.Constants.Acl;
import org.chocodev.util.Constants.ContentDisposition;

public class UploadHandlerBuilder {
    private final Map<String, String> queryParams = new HashMap<>();

    private UploadHandlerBuilder() {
    }

    public UploadHandlerBuilder setExpire(String exp) {
        queryParams.put("expires", exp);
        return this;
    }

    public UploadHandlerBuilder setAppId(String appId) {
        queryParams.put("x-ut-identifier", appId);
        return this;
    }

    public UploadHandlerBuilder setFileName(String fileName) {
        queryParams.put("x-ut-file-name", fileName);
        return this;
    }

    public UploadHandlerBuilder setFileSize(long fileSize) {
        queryParams.put("x-ut-file-size", String.valueOf(fileSize));
        return this;
    }

    public UploadHandlerBuilder setFileType(String fileType) {
        queryParams.put("x-ut-file-type", fileType);
        return this;
    }

    public UploadHandlerBuilder setCustomId(String customId) {
        queryParams.put("x-ut-custom-id", customId);
        return this;

    }

    public UploadHandlerBuilder setContentDisposition(ContentDisposition contentDisposition) {
        queryParams.put("x-ut-content-disposition", contentDisposition.getValue());
        return this;

    }

    UploadHandlerBuilder setACL(Acl acl) {
        queryParams.put("x-ut-acl", acl.getValue());
        return this;

    }
    public static UploadHandlerBuilder builder(){
        return new UploadHandlerBuilder();
    }
    public Map<String, String> build(){
        return queryParams;
    }
}
