package org.chocodev.core;

import java.util.ArrayList;
import java.util.Arrays;

import org.chocodev.core.File.IUTFile;
import org.chocodev.internal.Exceptions.SDK.BadApiCallException;
import org.chocodev.util.ParametersValidator;
import org.chocodev.util.Constants.KeyType;
import org.chocodev.util.Constants.Messages;

public class FileKey {
    private final ArrayList<IUTFile> fileObjects;
    private final ArrayList<String> fileBasics;

    public FileKey(String... fileKey) {
        this.fileBasics = new ArrayList<String>();
        this.fileObjects = new ArrayList<IUTFile>();

        for (String key : fileKey) {
            ParametersValidator.validate(new BadApiCallException(Messages.fieldErrorMessage), key);
            fileBasics.add(key);
        }
    }
    public FileKey(IUTFile... fileKey) {
        this.fileBasics = new ArrayList<String>();
        this.fileObjects = new ArrayList<IUTFile>(Arrays.asList(fileKey));
        for (IUTFile file : fileKey) {
            ParametersValidator.validate(new BadApiCallException(Messages.fieldErrorMessage), file);
            fileObjects.add(file);
        }
    }

    public ArrayList<String> getFileKey() {
        return getFileKey(KeyType.FILE_KEY);
    }

    public ArrayList<String> getFileKey(KeyType type) {
        ArrayList<String> keys = new ArrayList<String>(fileBasics);
        boolean isCustomId = type.equals(KeyType.CUSTOM_ID);
        return filterOnNull(keys, isCustomId);
    }

    private ArrayList<String> filterOnNull(ArrayList<String> keys, boolean isCustomId) {
        for (IUTFile File : fileObjects) {
            String key = File.getFileKey();
            if (isCustomId) {
                key = File.getCustomId();
            }
            ParametersValidator.validate(new BadApiCallException(Messages.emptyKeyErrorMessage), key);
            keys.add(key);
        }
        return keys;
    }
}
