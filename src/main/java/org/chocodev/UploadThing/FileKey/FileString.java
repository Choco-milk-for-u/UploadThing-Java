package org.chocodev.UploadThing.FileKey;

import java.util.ArrayList;

import org.chocodev.UploadThing.Constants.KeyType;
import org.chocodev.UploadThing.File.IUploadThingFile;

public class FileString {
    private final ArrayList<String> fileKeys;
    private final ArrayList<String> customIds = new ArrayList<String>();

    public FileString(FileKeys FileKeys) {
        if (FileKeys.getIsBasicData()) {
            fileKeys = FileKeys.files;
            return;
        }
        fileKeys = new ArrayList<String>();
        for (IUploadThingFile File : FileKeys.UploadThingFiles) {
            fileKeys.add(File.getKey());
            customIds.add(File.getCustomId());
        }
    }

    public ArrayList<String> getFile() {
        return fileKeys;
    }

    public ArrayList<String> getFile(KeyType keyType) {
        if (keyType.equals(KeyType.CUSTOM_ID)) {
            return customIds;
        }
        return getFile();
    }

}
