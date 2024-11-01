package org.chocodev.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.chocodev.core.File.IUTFile;
import org.chocodev.util.Constants.KeyType;

public class FileKey {
    private final ArrayList<IUTFile> fileObjects;
    private final ArrayList<String> fileBasics;

    public FileKey(String... fileKey) {
        this.fileBasics = new ArrayList<String>(Arrays.asList(fileKey));
        this.fileObjects = new ArrayList<IUTFile>();
    }

    public FileKey(IUTFile... fileKey) {
        this.fileBasics = new ArrayList<String>();
        this.fileObjects = new ArrayList<IUTFile>(Arrays.asList(fileKey));
    }

    public ArrayList<String> getFileKey() {
        return getFileKey(KeyType.FILE_KEY);
    }

    public ArrayList<String> getFileKey(KeyType type) {
        ArrayList<String> keys = new ArrayList<String>(fileBasics);
        List<String> objectKeys = fileObjects.stream().map((File) -> {
            if (type.equals(KeyType.CUSTOM_ID)) {
                return File.getCustomId();
            }
            return File.getFileKey();
        }).toList();
        keys.addAll(objectKeys);
        return keys;
    }
}
