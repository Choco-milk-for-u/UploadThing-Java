package org.chocodev.uploadthing.core.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.uploadthing.core.api.File.IUTFile;
import org.chocodev.uploadthing.core.internal.Messages;
import org.chocodev.uploadthing.core.util.ParametersValidator;
import org.chocodev.uploadthing.core.util.Constants.KeyType;

public class FileKey {
    private final ArrayList<IUTFile> fileObjects = new ArrayList<IUTFile>();
    private final ArrayList<String> fileBasics = new ArrayList<String>();

    private FileKey() {

    }

    public List<String> getFileKey() {
        return getFileKey(KeyType.FILE_KEY);
    }

    public List<String> getFileKey(KeyType type) {
        boolean isCustomId = type.equals(KeyType.CUSTOM_ID);
        return filterOnNull(isCustomId);
    }

    private List<String> filterOnNull(boolean isCustomId) {
        List<String> keys = new ArrayList<>();
        processFileBasics(keys);
        processFileObjects(keys, isCustomId);
        return keys;
    }

    private void processFileBasics(List<String> keys) {
        for (String file : fileBasics) {
            addValidatedKey(keys, file);
        }
    }

    private void processFileObjects(List<String> keys, boolean isCustomId) {
        for (IUTFile file : fileObjects) {
            String key = isCustomId ? file.getCustomId() : file.getFileKey();
            addValidatedKey(keys, key);
        }
    }

    private void addValidatedKey(List<String> keys, String key) {
        ParametersValidator.validate(new BadApiCallException(Messages.EMPTY_KEY_ERROR_MESSAGE), key);
        keys.add(key);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final FileKey Key;

        public Builder() {
            this.Key = new FileKey();
        }

        public Builder setFileKey(String... fileKey) {
            Key.fileBasics.addAll(Arrays.asList(fileKey));
            return this;
        }

        public Builder setFileKey(IUTFile... FileKey) {
            Key.fileObjects.addAll(Arrays.asList(FileKey));
            return this;
        }

        public FileKey build() {
            if (Key.fileBasics.isEmpty() && Key.fileObjects.isEmpty()) {
                throw new BadApiCallException(Messages.BAD_BUILDER_BUILD);
            }
            return Key;
        }
    }
}
