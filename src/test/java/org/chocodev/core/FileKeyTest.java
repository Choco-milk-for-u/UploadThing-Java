package org.chocodev.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.chocodev.core.File.IUTFile;
import org.chocodev.internal.Exceptions.SDK.BadApiCallException;
import org.chocodev.util.Constants.KeyType;
import org.junit.jupiter.api.Test;

public class FileKeyTest {
    @Test
    public void createFileKeyWithoutArg(){
        assertThrows(BadApiCallException.class, ()->FileKey.builder().build());
    } 
    @Test
    public void createFileKeyWithoutData(){
        FileKey Key = FileKey.builder().setFileKey("").build();
        assertThrows(BadApiCallException.class, ()->Key.getFileKey());
    } 
    @Test
    public void getKey(){
        FileKey Key = FileKey.builder().setFileKey("normal key").build();
        assertEquals("normal key", Key.getFileKey().get(0));
    }
    @Test
    public void getWrongKey(){
        IUTFile File = mock(IUTFile.class);
        FileKey Key = FileKey.builder().setFileKey(File).build();
        when(File.getCustomId()).thenReturn(null);
        assertThrows(BadApiCallException.class, ()->Key.getFileKey(KeyType.CUSTOM_ID));
    }
}
