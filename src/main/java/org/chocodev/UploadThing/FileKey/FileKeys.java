package org.chocodev.UploadThing.FileKey;

import java.util.ArrayList;

import org.chocodev.UploadThing.File.IUploadThingFile;

public class FileKeys {
    protected final ArrayList<String> files = new ArrayList<String>();
    protected IUploadThingFile[] UploadThingFiles;
    private final boolean isBasic;

    public boolean getIsBasicData() {
        return this.isBasic;
    }

    public FileKeys(String... files) {
        isBasic = true;
        for (String file : files) {
            this.files.add(file);
        }
    }

    public FileKeys(IUploadThingFile... Files) {
        isBasic = false;
        this.UploadThingFiles = Files;
    }

}
