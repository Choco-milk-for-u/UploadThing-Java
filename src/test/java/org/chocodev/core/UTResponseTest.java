package org.chocodev.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.chocodev.core.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

import okhttp3.Response;

public class UTResponseTest {
    @Test
    public void ResponseException(){
        Exception exception = new Exception("error message");
        UTResponse<String> Response = new UTResponse<>(exception);
        assertTrue(Response.hasException());
        assertThrows(Exception.class, ()->Response.throwIfException());
        assertThrows(BadApiCallException.class, ()->Response.throwIfException((String m)->new BadApiCallException(m)));
        assertNull(Response.getBody());
        assertFalse(Response.isOk());
        assertEquals(-1, Response.getStatus());
    }
    @Test
    public void ResponseNormal(){
        Response response = mock(Response.class);
        when(response.code()).thenReturn(200);
        when(response.isSuccessful()).thenReturn(true);
        UTResponse<String> Response = new UTResponse<String>(response, "result");
        assertFalse(Response.hasException());
        assertTrue(Response.isOk());
        assertNotNull(Response.getBody());
        assertEquals(200, Response.getStatus());
        assertDoesNotThrow(()->Response.throwIfException());
        assertDoesNotThrow(()->Response.throwIfException((String m)->new BadApiCallException(m)));
    }
    @Test
    public void ResponseWithoutException(){
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(null));
    }
    @Test
    public void ResponseWithoutResponse(){
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(null, "body"));
    }
    @Test
    public void ResponseWithoutBody(){
        Response response = mock(Response.class);
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(response, null));
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(response, ""));

    }
}
