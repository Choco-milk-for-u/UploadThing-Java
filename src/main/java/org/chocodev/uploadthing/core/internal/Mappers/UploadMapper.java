package org.chocodev.uploadthing.core.internal.Mappers;

import org.chocodev.uploadthing.core.api.UTFile;
import org.chocodev.uploadthing.core.api.UploadParameters;
import org.chocodev.uploadthing.core.api.Options.UploadOptions;
import org.chocodev.uploadthing.core.util.UploadParametersBuilder;

public class UploadMapper {
    public static UploadParameters transform(UTFile File, UploadOptions Options, String appId) {
        UploadParametersBuilder builder = UploadParametersBuilder
                .builder(File.getName(), File.getFileSize(), appId);
        if (File.getCustomId() != null) {
            builder.setCustomId(File.getCustomId());
        }
        if (Options.getContentDisposition() != null) {
            builder.setContentDisposition(Options.getContentDisposition());
        }
        if (Options.getAcl() != null) {
            builder.setACL(Options.getAcl());
        }
        return builder.build();
    }
}
