# Unofficial Java SDK for UploadThing.

#### Currently SDK primaly focuses on [The UploadThing API Helper](https://docs.uploadthing.com/api-reference/ut-api) only.

###### The documintation below include basic usage considerations. Anything not listed below works similarly to the original [docs](https://docs.uploadthing.com).

###### _Feel free to open a ticket. I will personally resolve it as long as this message remains here._

## Instalation

maven
```bash
<dependency>
    <groupId>org.chocodev.uploadthing</groupId>
    <artifactId>core</artifactId>
    <version>1.0</version> # please include latest version.
</dependency>
```
gradle
```bash
implementation group: 'org.chocodev.uploadthing', name: 'core', version: '1.0'
```
or other methods, that can be found at [maven central](https://central.sonatype.com/artifact/org.chocodev.uploadthing/core/overview)
## Usage

```java
UTApi utApi = new UTApi(token); //v7 token only

utApi.uploadFiles(File);
```

## UTFile

Representation of the file for the upload.

```java
UTFile File = UTFile.bulder()
                    .setBytes(bytes)
                    .setName(path.getFileName().toString())
                    .setSize(Files.size(path))
                    .setLastModified(Files.getLastModifiedTime(path).toMillis())
                    .setType("img/jpeg")
                    .setCustomId(customId)
                    .build();
```

## UTResponse

Every UTApi request returns a custom UTResponse object.

```java
  UTResponse<DeleteResponse> response = utApi.deleteFiles(File);
        if(response.hasException()){
            // code if an exception occurred
        }
        if(response.isOk()){
            // code on success
        }
        if(response.getStatus() == 404){
            // ...
        }
        response.getBody(); // body of the response
        response.throwIfException(); // throw the original exception
        response.throwIfException((m)->new BadApiCallException(m)); // or your custom one.
```

#### uploade files

Upload a file or files. You can also specify the type of some response data.
_When uploading several files, the response body will contain the upload count. (count of only successfully uploaded files)_

```java
UTResponse<UploadPerRequest<Long>> response = utApi.uploadFiles<Long>(File);
UTResponse<UploadPerRequest<Long>> response = utApi.uploadFiles(File, UploadOptions);
UTResponse<UploadResponse<Long>> response = utApi.uploadFiles(List.of(File, SecondFile), UploadOptions);
```

#### delete files

Delete a file or files by FileKey.

```java
UTResponse<DeleteResponse> response = utApi.deleteFiles(FileKey);
UTResponse<DeleteResponse> response = utApi.deleteFiles(FileKey, DeleteOptions);
```

## FileKey

This object is very useful in cases when the rest API accepts custom IDs or file keys. It accepts one or several existing keys or can take keys from IUTFile class implementations (file representation from Rest API responses).

```java
  IUTFile ResponseFile = // UT rest api call.
  FileKey.builder()
          .setFileKey(ResponseFile)
          .setFileKey("fileKey")
          .setFileKey("secondKey", "thirdKey")
          .build();
```

## IUTFile

An interface exclusively for **response** representation of UploadThing files. It is implemented by subclasses of BasicFile.

## FAQ

> The method requires the object that cannot be initialized.

###### _In such cases, the object must have a static builder class that can be created either by calling the static method of that object or using a separate builder class._

> How can i send a file?

###### _By using the *UTFile* class, you can create a file object that must have a byte representation of the file.._
