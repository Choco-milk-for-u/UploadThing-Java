package org.chocodev.Fetch;

import java.net.http.HttpClient;
import org.chocodev.Responses.GetFile;
import org.chocodev.UploadThing.File.IFile;

public class HttpNet implements IFetchFactory {
    HttpClient Client = HttpClient.newHttpClient();

    @Override
    public IFetch<IFile> getFile(String url) {
        return new GetFile(Client, url);
    }

}
