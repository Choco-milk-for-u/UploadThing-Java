package org.chocodev.core;

import java.util.HashMap;
import java.util.Map;

public class UploadParameters {
    protected final UploadParameters UploadParameters;
    protected Map<String, String> query = new HashMap<>();

    public Map<String, String> getQuery() {
        return query;
    };

    protected UploadParameters() {
        this.UploadParameters = this;
    }
}
