package org.chocodev.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.chocodev.internal.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

public class FileKeyTest {
    @Test
    public void createFileKeyWithoutString(){
        assertThrows(BadApiCallException.class, ()->new FileKey(""));
        assertThrows(BadApiCallException.class, ()->new FileKey("", ""));

    } 
    // later implement
    @Test
    public void createFileKeyWithoutFile(){
        assertFalse(true);
        assertThrows(BadApiCallException.class, ()->new FileKey(""));
    } 
}
