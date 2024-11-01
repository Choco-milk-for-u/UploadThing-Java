package org.chocodev.util;

import org.chocodev.internal.Exceptions.SDK.MapperException;
import org.chocodev.util.Constants.Messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <TClass> TClass readValue(String body, Class<TClass> valueType) {
        try {
            return objectMapper.readValue(body, valueType);
        } catch (JsonProcessingException e) {
            throw new MapperException(Messages.mapperErrorMessage);
        }
    }

    public static String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new MapperException(Messages.mapperErrorMessage);
        }
    }

    private Mapper() {

    }
}
