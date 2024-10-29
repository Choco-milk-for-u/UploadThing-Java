package org.chocodev.UploadThing.FileKey;

import java.util.ArrayList;

import org.chocodev.UploadThing.Constants.KeyType;
import org.chocodev.UploadThing.File.IUploadThingFile;

public class FileString {
    private final FileKeys FileKeys;

    public FileString(FileKeys FileKeys) {
        this.FileKeys = FileKeys;
    }

    public String getFromBasic() {
        return FileKeys.files.size() > 1 ? FileKeys.files.toString() : FileKeys.files.get(0);
    }

    public String getFromFiles(KeyType keyType) {
        ArrayList<String> toStrings = new ArrayList<String>();
        for (IUploadThingFile File : FileKeys.UploadThingFiles) {
            if (keyType.equals(KeyType.CUSTOM_ID)) {
                toStrings.add(File.getCustomId());
            }
            if (keyType.equals(KeyType.FILE_KEY)) {
                toStrings.add(File.getKey());
            }
        }
        return toStrings.size() > 1 ? toStrings.toString() : toStrings.get(0);
    }

}
