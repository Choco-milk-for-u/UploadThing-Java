package org.chocodev.core.Responses;

import java.util.ArrayList;
import org.chocodev.core.UTResponse;

public class UploadResponse<TServerData> extends BasicResponse {
    private final ArrayList<UTResponse<UploadPerRequest<TServerData>>> result;
    private final int uploadCount;

    public UploadResponse(ArrayList<UTResponse<UploadPerRequest<TServerData>>> result, int uploadCount) {
        this.result = result;
        this.uploadCount = uploadCount;
    }

    public int getUploadCount() {
        return uploadCount;
    }

    public ArrayList<UTResponse<UploadPerRequest<TServerData>>> getRespones() {
        return result;
    }
}
