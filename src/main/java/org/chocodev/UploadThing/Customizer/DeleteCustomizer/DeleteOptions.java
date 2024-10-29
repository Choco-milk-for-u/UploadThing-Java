package org.chocodev.UploadThing.Customizer.DeleteCustomizer;

import java.util.ArrayList;
import java.util.Map;

import org.chocodev.Error.ApiException;
import org.chocodev.Requests.RequestMapper;
import org.chocodev.UploadThing.Constants.KeyType;
import org.chocodev.UploadThing.Constants.Messages;
import org.chocodev.UploadThing.Customizer.ICustomizer;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.FileKey.FileString;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DeleteOptions {
    private KeyType keyType = KeyType.FILE_KEY;

    public String getJson(FileKeys Files) {
        try {
            return RequestMapper.objectMapper.writeValueAsString(Map.of(keyType.getValue(), returnFiles(Files)));
        } catch (JsonProcessingException e) {
            throw new ApiException(Messages.apiFileKeysException);
        }
    }

    private ArrayList<String> returnFiles(FileKeys Files) {
        ArrayList<String> filesStrings = new ArrayList<String>();
        FileString FileString = new FileString(Files);
        filesStrings = Files.getIsBasicData() ? FileString.getFromBasic() : FileString.getFromFiles(keyType);
        return filesStrings;
    };

    private DeleteOptions() {
    }

    public static class Customizer implements ICustomizer<DeleteOptions> {
        private DeleteOptions Options;

        @Override
        public ICustomizer<DeleteOptions> builder() {
            Options = new DeleteOptions();
            return this;
        }

        @Override
        public DeleteOptions build() {
            if(Options == null){
                throw new ApiException(Messages.apiBuilderException);
            }
            return Options;
        }

        public Customizer setType(KeyType keyType) {
            Options.keyType = keyType;
            return this;
        }

        @Override
        public DeleteOptions withDefault() {
            return new DeleteOptions();

        }
    }
}
