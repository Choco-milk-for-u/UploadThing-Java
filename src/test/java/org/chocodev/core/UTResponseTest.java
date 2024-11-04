package org.chocodev.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import javax.net.ssl.SSLSession;

import org.chocodev.core.Exceptions.SDK.BadApiCallException;
import org.junit.jupiter.api.Test;

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
        HttpResponse<String> response = new HttpResponse<String>() {

            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'request'");
            }

            @Override
            public Optional<HttpResponse<String>> previousResponse() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'previousResponse'");
            }

            @Override
            public HttpHeaders headers() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'headers'");
            }

            @Override
            public String body() {
                return "result";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'sslSession'");
            }

            @Override
            public URI uri() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'uri'");
            }

            @Override
            public Version version() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'version'");
            }
            
        };
        UTResponse<String> Response = new UTResponse<>(response, "result");
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
        HttpResponse<String> response = mock(HttpResponse.class);
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(response, null));
        assertThrows(BadApiCallException.class, ()->new UTResponse<>(response, ""));

    }
}
