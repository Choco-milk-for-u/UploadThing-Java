package org.chocodev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.chocodev.Error.ApiFieldException;
import org.chocodev.Error.RequestException;
import org.chocodev.Fetch.HttpNet;
import org.chocodev.Responses.GetFile;
import org.chocodev.UploadThing.File.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UTApiTest {
    @Mock
    private HttpNet Fetcher;
    private UTApi utApi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        utApi = new UTApi(Fetcher);
    }

    // test get file with file key only
    @Test
    public void getFile() throws RequestException, ApiFieldException {
        String fileKey = "rightKey";
        GetFile Request = mock(GetFile.class);
        File File = mock(File.class);
        when(Fetcher.getFile("/f/" + fileKey)).thenReturn(Request);
        when(Request.request()).thenReturn(File);
        File Result = utApi.<File>getFile(fileKey);
        assertEquals(File, Result);
    }

    // test get file with file key and app id
    @Test
    public void getFileFull() throws RequestException, ApiFieldException {
        String fileKey = "rightKey";
        String appId = "rightId";
        GetFile Request = mock(GetFile.class);
        File File = mock(File.class);
        when(Fetcher.getFile("/a/" + appId + "/" + fileKey)).thenReturn(Request);
        when(Request.request()).thenReturn(File);
        File Result = utApi.<File>getFile(fileKey, appId);
        assertEquals(File, Result);
    }

    @Test
    public void getFileWithoutFileKey() throws RequestException {
        String fileKey = "";
        assertThrows(ApiFieldException.class, () -> utApi.<File>getFile(fileKey));
    }

    @Test
    public void getFileWithoutOne() throws RequestException {
        String fileKey = null;
        String appId = "rightId";
        assertThrows(ApiFieldException.class, () -> utApi.<File>getFile(fileKey, appId));

    }

}
