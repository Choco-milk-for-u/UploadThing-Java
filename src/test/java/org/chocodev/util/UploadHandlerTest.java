package org.chocodev.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.chocodev.uploadthing.core.api.Exceptions.SDK.FieldException;
import org.chocodev.uploadthing.core.util.UploadHandler;
import org.junit.jupiter.api.Test;

public class UploadHandlerTest {
    String[] arr = {"a", "b"};
    private final UploadHandler UploadHandler = new UploadHandler("key", "appid", arr);
    @Test
    public void generateKey(){
        assertThrows(FieldException.class, ()->UploadHandler.generateKey(null));
    }
    @Test
    public void createSignedUrlWithoutFileKey(){
        Map<String,String> map = new HashMap<>();
        assertThrows(FieldException.class, ()->UploadHandler.createSignedUploadUrl("", map));
    }
    @Test
    public void createSignedUrlWithoutParams(){
        assertThrows(FieldException.class, ()->UploadHandler.createSignedUploadUrl("fileKey", null));
    }
}
