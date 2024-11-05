package org.chocodev.internal.Services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.chocodev.core.FileKey;
import org.chocodev.core.UTFile;
import org.chocodev.core.Exceptions.SDK.FieldException;
import org.chocodev.core.Options.DeleteOption.DeleteOptions;
import org.chocodev.util.UploadHandler;
import org.junit.jupiter.api.Test;

public class ServiceFactoryTest {
    private final ServiceFactory serviceFactory = new ServiceFactory("key");

    @Test
    public void deleteFilesWithoutFile() {
        assertThrows(FieldException.class, () -> serviceFactory.getDeleteService(null, DeleteOptions.withDefault()));
    }

    @Test
    public void deleteFilesWithoutOption() {
        assertThrows(FieldException.class,
                () -> serviceFactory.getDeleteService(FileKey.builder().setFileKey("s").build(), null));
    }

    @Test
    public void uploadFilesWithoutField() {
        assertThrows(FieldException.class,
                () -> serviceFactory.getUploadService(any(UploadHandler.class), null, any(UTFile.class)));
    }


}
