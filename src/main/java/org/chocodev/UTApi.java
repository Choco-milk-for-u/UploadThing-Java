package org.chocodev;

import org.chocodev.Error.SDK.FieldException;
import org.chocodev.Error.UTApi.RequestException;
import org.chocodev.Fetch.HttpNet;
import org.chocodev.Fetch.IFetchFactory;
import org.chocodev.UploadThing.Constants.Messages;
import org.chocodev.UploadThing.Customizer.DeleteCustomizer.DeleteOptions;
import org.chocodev.UploadThing.File.IFile;
import org.chocodev.UploadThing.FileKey.FileKeys;
import org.chocodev.UploadThing.Status.IStatus;

public class UTApi {
    private final IFetchFactory Fetcher;

    public UTApi(IFetchFactory Fetcher, String token) {
        Fetcher.setToken(token);
        this.Fetcher = Fetcher;
    }

    public UTApi(String token) {
        HttpNet HttpNet = new HttpNet();
        HttpNet.setToken(token);
        this.Fetcher = HttpNet;
    }

    public <TFile extends IFile> TFile getFile(String fileKey, String appId)
            throws RequestException {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), fileKey, appId);
        return Fetcher.<TFile>getFile("/a/" + appId + "/" + fileKey).request();
    }

    public <TFile extends IFile> TFile getFile(String fileKey) throws RequestException {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), fileKey);
        return Fetcher.<TFile>getFile("/f/" + fileKey).request();
    }

    public <TStatus extends IStatus> TStatus deleteFile(FileKeys File, DeleteOptions Options)
            throws RequestException {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), File, Options);
        return Fetcher.<TStatus>deleteFile(File, Options).request();
    }

    public <TStatus extends IStatus> TStatus deleteFile(FileKeys File) throws RequestException {
        ParametersValidator.validate(new FieldException(Messages.fieldErrorMessage), File);
        return Fetcher.<TStatus>deleteFile(File, new DeleteOptions.Customizer().withDefault()).request();
    }
}
