package org.chocodev.Fetch;

import java.net.http.HttpClient;
import org.chocodev.Requests.DeleteFile;
import org.chocodev.Requests.GetFile;
import org.chocodev.UploadThing.Customizer.DeleteCustomizer.DeleteOptions;
import org.chocodev.UploadThing.File.IFile;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.Status.IStatus;

public class HttpNet implements IFetchFactory {
    private final HttpClient Client = HttpClient.newHttpClient();
    private String utApiToken;

    @Override
    public IFetch<IFile> getFile(String url) {
        return new GetFile(Client, url);
    }

    @Override
    public IFetch<IStatus> deleteFile(FileKeys Files, DeleteOptions Options) {
        return new DeleteFile(Files, utApiToken, Options, Client);
    }

    @Override
    public void setToken(String token) {
        this.utApiToken = token;
    }
}
