package org.chocodev.core.Options;

import java.util.Map;

import org.chocodev.core.FileKey;
import org.chocodev.util.Mapper;
import org.chocodev.util.Constants.KeyType;

public class DeleteOptions {
    private KeyType type = KeyType.FILE_KEY;

    private DeleteOptions() {
    }

    public String getRequestBody(FileKey File) {
        return Mapper.writeValueAsString(Map.of(type.getValue(), File.getFileKey(type)));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static DeleteOptions withDefault() {
        return new DeleteOptions();
    }

    public static class Builder {
        private DeleteOptions Option = new DeleteOptions();

        public DeleteOptions build() {
            return Option;
        }

        public Builder setType(KeyType type) {
            this.Option.type = type;
            return this;
        }
    }
}
