package org.chocodev.api.Responses;

import java.util.List;

import org.chocodev.api.UTResponse;

public class UploadResponse<TServerData> extends BasicResponse {
    private final List<UTResponse<UploadPerRequest<TServerData>>> result;
    private final int uploadCount;

    public UploadResponse(List<UTResponse<UploadPerRequest<TServerData>>> result, int uploadCount) {
        this.result = result;
        this.uploadCount = uploadCount;
    }

    public int getUploadCount() {
        return uploadCount;
    }

    public List<UTResponse<UploadPerRequest<TServerData>>> getRespones() {
        return result;
    }
}
