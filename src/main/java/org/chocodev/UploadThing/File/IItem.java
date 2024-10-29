package org.chocodev.UploadThing.File;

import java.util.Map;

import org.chocodev.UploadThing.Constants.ContentDisposition;

public interface IItem extends IUploadThingFile{
    public String getFileUrl();
    public String getFileType();
    public ContentDisposition getContentDisposition();
    public String getUrl();
    public String getPollingUrl();
    public String getPollingJwt();
    public Map<String, Object> getFields();
}