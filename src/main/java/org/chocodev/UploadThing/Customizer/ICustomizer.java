package org.chocodev.UploadThing.Customizer;

public interface ICustomizer<TObject> {
    public ICustomizer<TObject> builder();
    public TObject build();
    public TObject withDefault();
}
