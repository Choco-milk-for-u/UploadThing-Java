package org.chocodev;

import org.chocodev.Error.ApiFieldException;
import org.chocodev.Error.RequestException;
import org.chocodev.Fetch.HttpNet;
import org.chocodev.Fetch.IFetchFactory;
import org.chocodev.UploadThing.File.IFile;

public class UTApi {
    private final IFetchFactory Fetcher;
    private final ParametersValidator parametersValidator = new ParametersValidator();

    public UTApi(IFetchFactory Fetcher) {
        this.Fetcher = Fetcher;
    }

    public UTApi() {
        this.Fetcher = new HttpNet();
    }

    public <TFile extends IFile> TFile getFile(String fileKey, String appId)
            throws RequestException, ApiFieldException {
        parametersValidator.validate(new ApiFieldException(UTApiConfig.apiFieldError), fileKey, appId);
        return Fetcher.<TFile>getFile("/a/" + appId + "/" + fileKey).request();
    }

    public <TFile extends IFile> TFile getFile(String fileKey) throws RequestException, ApiFieldException {
        parametersValidator.validate(new ApiFieldException(UTApiConfig.apiFieldError), fileKey);
        return Fetcher.<TFile>getFile("/f/" + fileKey).request();
    }
}
