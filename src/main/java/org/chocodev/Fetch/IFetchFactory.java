package org.chocodev.Fetch;

import org.chocodev.UploadThing.Customizer.DeleteCustomizer.DeleteOptions;
import org.chocodev.UploadThing.File.IFile;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.Status.IStatus;

public interface IFetchFactory {
    public void setToken(String token);

    public <TFile extends IFile> IFetch<TFile> getFile(String url);

    public <TStatus extends IStatus> IFetch<TStatus> deleteFile(FileKeys File, DeleteOptions Options);
}