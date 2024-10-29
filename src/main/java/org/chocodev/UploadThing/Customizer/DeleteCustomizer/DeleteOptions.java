package org.chocodev.UploadThing.Customizer.DeleteCustomizer;

import java.util.Map;
import java.util.ArrayList;

import org.chocodev.Error.SDK.CustomizerException;
import org.chocodev.Error.SDK.MapperException;
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
            throw new MapperException(Messages.mapperErrorMessage);
        }
    }

    private ArrayList<String> returnFiles(FileKeys Files) {
        FileString FileString = new FileString(Files);
        ArrayList<String> filesStrings = Files.getIsBasicData() ? FileString.getFile() : FileString.getFile(keyType);
        return filesStrings;
    };

    private DeleteOptions() {
    }

    public static class Customizer implements ICustomizer<DeleteOptions> {
        private DeleteOptions Options;

        @Override
        public Customizer builder() {
            Options = new DeleteOptions();
            return this;
        }

        @Override
        public DeleteOptions build() {
            if(Options == null){
                throw new CustomizerException();
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
