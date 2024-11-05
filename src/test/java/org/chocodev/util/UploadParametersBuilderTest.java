package org.chocodev.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.chocodev.core.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

public class UploadParametersBuilderTest {
    @Test
    public void buildWithoutOneField(){
        assertThrows(BadApiCallException.class, ()->UploadParametersBuilder.builder("filename", 0, ""));
    }
}
