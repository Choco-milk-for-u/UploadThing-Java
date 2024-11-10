package org.chocodev.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.chocodev.uploadthing.core.util.UploadParametersBuilder;
import org.junit.jupiter.api.Test;

public class UploadParametersBuilderTest {
    @Test
    public void buildWithoutOneField(){
        assertThrows(BadApiCallException.class, ()->UploadParametersBuilder.builder("filename", 0, ""));
    }
}
