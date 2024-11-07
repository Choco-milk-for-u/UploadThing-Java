package org.chocodev.internal.Mappers;

import org.chocodev.core.UTFile;
import org.chocodev.core.UploadParameters;
import org.chocodev.core.Options.UploadOptions;
import org.chocodev.util.UploadParametersBuilder;

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
