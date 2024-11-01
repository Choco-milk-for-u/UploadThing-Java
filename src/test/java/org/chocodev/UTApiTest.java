package org.chocodev;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class UTApiTest {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // utApi = new UTApi(Fetcher, "token");
    }
}
