package org.chocodev.UploadThing.File;

public interface IFile {
  public byte[] getBytes();
  public String getName();
  public String getType();
  public String getFileExtension();
}
