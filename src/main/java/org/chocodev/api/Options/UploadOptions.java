package org.chocodev.api.Options;

import org.chocodev.util.Constants.Acl;
import org.chocodev.util.Constants.ContentDisposition;

public class UploadOptions {

    private UploadOptions() {
    }

    private Acl acl;
    private ContentDisposition contentDisposition;

    public Acl getAcl() {
        return acl;
    }

    public ContentDisposition getContentDisposition() {
        return contentDisposition;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final UploadOptions Options;

        private Builder() {
            Options = new UploadOptions();
        }

        public UploadOptions build() {
            return Options;
        }

        public Builder setContentDisposition(ContentDisposition contentDisposition) {
            this.Options.contentDisposition = contentDisposition;
            return this;
        }

        public Builder setACL(Acl acl) {
            this.Options.acl = acl;
            return this;
        }
    }
}
