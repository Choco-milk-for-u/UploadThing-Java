package org.chocodev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.chocodev.Error.SDK.FieldException;
import org.chocodev.Error.UTApi.RequestException;
import org.chocodev.Fetch.HttpNet;
import org.chocodev.Requests.DeleteFile;
import org.chocodev.Requests.GetFile;
import org.chocodev.UploadThing.Customizer.DeleteCustomizer.DeleteOptions;
import org.chocodev.UploadThing.File.FileData;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.Status.DeleteStatus;
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
        utApi = new UTApi(Fetcher, "token");
    }

    // test get file with file key only
    @Test
    public void getFile() throws RequestException {
        String fileKey = "rightKey";
        GetFile Request = mock(GetFile.class);
        FileData File = mock(FileData.class);
        when(Fetcher.getFile("/f/" + fileKey)).thenReturn(Request);
        when(Request.request()).thenReturn(File);
        FileData Result = utApi.<FileData>getFile(fileKey);
        assertEquals(File, Result);
    }

    // test get file with file key and app id
    @Test
    public void getFileFull() throws RequestException {
        String fileKey = "rightKey";
        String appId = "rightId";
        GetFile Request = mock(GetFile.class);
        FileData File = mock(FileData.class);
        when(Fetcher.getFile("/a/" + appId + "/" + fileKey)).thenReturn(Request);
        when(Request.request()).thenReturn(File);
        FileData Result = utApi.<FileData>getFile(fileKey, appId);
        assertEquals(File, Result);
    }

    @Test
    public void getFileWithoutFileKey() throws RequestException {
        String fileKey = "";
        assertThrows(FieldException.class, () -> utApi.<FileData>getFile(fileKey));
    }

    @Test
    public void getFileWithoutOne() throws RequestException {
        String fileKey = null;
        String appId = "rightId";
        assertThrows(FieldException.class, () -> utApi.<FileData>getFile(fileKey, appId));

    }

    @Test
    public void deleteFile() throws RequestException {
        DeleteFile Request = mock(DeleteFile.class);
        DeleteOptions Options = new DeleteOptions.Customizer().withDefault();
        DeleteStatus Status = new DeleteStatus();
        FileKeys File = new FileKeys("filekey");
        Status.setDeletedCount(1);
        Status.setSuccess(true);
        when(Fetcher.deleteFile(File, Options)).thenReturn(Request);
        when(Request.request()).thenReturn(Status);
        DeleteStatus Result = utApi.<DeleteStatus>deleteFile(File, Options);
        assertEquals(Status, Result);
    }
    @Test
    public void deleteFileWithoutFile() throws RequestException {
        assertThrows(FieldException.class, () -> utApi.deleteFile(null));
    }

    @Test
    public void deleteFileWithoutOptions() throws RequestException {
        assertThrows(FieldException.class, () -> utApi.deleteFile(mock(FileKeys.class), null));
    }
}
