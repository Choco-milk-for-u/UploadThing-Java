package org.chocodev.uploadthing.core.util;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.MapperException;
import org.chocodev.uploadthing.core.internal.Messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <TClass> TClass readValue(String body, Class<TClass> valueType) {
        try {
            return objectMapper.readValue(body, valueType);
        } catch (JsonProcessingException e) {
            throw new MapperException(Messages.MAPPER_ERROR_MESSAGE);
        }
    }

    public static String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new MapperException(Messages.MAPPER_ERROR_MESSAGE);
        }
    }

    private Mapper() {

    }
}
