package org.chocodev.uploadthing.core.api.File;

/**
 * Basic class usually to be extended.
 * On every openapi request call that returns the file, BasicFile subclass will be return.
 */

public class BasicFile implements IUTFile {

    @Override
    public String getCustomId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomId'");
    }

    @Override
    public String getFileKey() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileKey'");
    }

    @Override
    public String getFileName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileName'");
    }
    
}
