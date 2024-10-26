package org.chocodev.Fetch;

import org.chocodev.UploadThing.File.IFile;

public interface IFetchFactory {
    public <TFile extends IFile> IFetch<TFile> getFile(String url);
}
