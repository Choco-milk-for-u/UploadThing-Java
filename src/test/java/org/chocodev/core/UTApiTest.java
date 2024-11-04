package org.chocodev.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.net.http.HttpResponse;

import org.chocodev.core.Exceptions.SDK.FieldException;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.core.Responses.DeleteResponse;
import org.chocodev.internal.Services.DeleteService;
import org.chocodev.internal.Services.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UTApiTest {
    @Mock
    ServiceFactory serviceFactory;
    @Mock
    DeleteService deleteService;
    @InjectMocks
    private UTApi utApi;

    @BeforeEach
    public void setup()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        utApi = new UTApi("token");
        Field field = utApi.getClass().getDeclaredField("ServiceFactory");
        field.setAccessible(true);
        field.set(utApi, serviceFactory);

    }

    @Test
    public void deleteFiles() {
        DeleteResponse DeleteResponse = new DeleteResponse();
        DeleteResponse.setDeletedCount(1);
        DeleteOptions Options = DeleteOptions.withDefault();
        DeleteResponse.setSuccess(true);
        FileKey Key = FileKey.builder().setFileKey("s").build();
        HttpResponse<String> response = mock(HttpResponse.class);
        UTResponse<DeleteResponse> Responses = new UTResponse<DeleteResponse>(response, DeleteResponse);
        // when
        when(response.statusCode()).thenReturn(200);
        when(serviceFactory.getDeleteService(Key, Options)).thenReturn(deleteService);
        when(deleteService.request(any())).thenReturn(Responses);
        // then
        UTResponse<DeleteResponse> ResultSecond = utApi.deleteFiles(Key, Options);
        assertEquals(ResultSecond, Responses);
    }

    @Test
    public void deleteFilesWithoutFile() {
        assertThrows(FieldException.class, () -> utApi.deleteFiles(null, DeleteOptions.withDefault()));
    }

    @Test
    public void deleteFilesWithoutOption() {
        assertThrows(FieldException.class, () -> utApi.deleteFiles(FileKey.builder().setFileKey("s").build(), null));
    }
}
