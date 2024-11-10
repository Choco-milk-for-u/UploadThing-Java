package org.chocodev.uploadthing.core.api.Options;

import java.util.Map;

import org.chocodev.uploadthing.core.api.FileKey;
import org.chocodev.uploadthing.core.util.Mapper;
import org.chocodev.uploadthing.core.util.Constants.KeyType;

/**
 * DeleteOptions is a class that can be used to contsruct options of delete
 * method.
 * the only way to initialize the class by using static method.
 */
public class DeleteOptions {
    private KeyType type = KeyType.FILE_KEY;

    private DeleteOptions() {
    }

    /**
     * transform keys to json represintation for body request.
     */
    public String getRequestBody(FileKey File) {
        return Mapper.writeValueAsString(Map.of(type.getValue(), File.getFileKey(type)));
    }

    /**
     * the method to start constructing options.
     * 
     * @return Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * By calling this, you let SDK decide which options to use.
     */
    public static DeleteOptions withDefault() {
        return new DeleteOptions();
    }

    /**
     * Static Builder for DeleteOptions
     */
    public static class Builder {
        private DeleteOptions Option = new DeleteOptions();

        /**
         * finish constructing DeleteOptions.
         */
        public DeleteOptions build() {
            return Option;
        }

        /**
         * @param type of KeyType.
         */
        public Builder setType(KeyType type) {
            this.Option.type = type;
            return this;
        }
    }
}
