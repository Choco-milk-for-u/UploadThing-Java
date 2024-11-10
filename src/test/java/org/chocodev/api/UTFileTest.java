package org.chocodev.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.chocodev.uploadthing.core.api.UTFile;
import org.chocodev.uploadthing.core.api.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

public class UTFileTest {
    @Test
    public void createBuildFileWithoutName(){
        assertThrows(BadApiCallException.class, ()->UTFile.bulder().build());
    }   
    @Test
    public void createBuildFileWithoutData(){
        assertThrows(BadApiCallException.class, ()->UTFile.bulder().setName("name").build());
    }   
}
