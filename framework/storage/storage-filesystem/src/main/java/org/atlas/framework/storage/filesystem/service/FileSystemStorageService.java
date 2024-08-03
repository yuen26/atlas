package org.atlas.framework.storage.filesystem.service;

import org.atlas.framework.storage.contract.StorageService;
import org.atlas.shared.util.function.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

@Component
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.filesystem.location}")
    private String location;

    @Override
    public void upload(String fileName, byte[] fileContent, Map<String, String> metadata, Callback<Void> callback) {
        File targetFile = createFile(fileName);
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(fileContent);
            callback.onSuccess(null);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void download(String fileName, Callback<byte[]> callback) {
        File sourceFile = createFile(fileName);
        try (InputStream fileStream = new FileInputStream(sourceFile)) {
            byte[] fileContent = fileStream.readAllBytes();
            callback.onSuccess(fileContent);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    @Override
    public void delete(String fileName, Callback<Void> callback) {
        File sourceFile = createFile(fileName);
        try {
            Files.delete(sourceFile.toPath());
            callback.onSuccess(null);
        } catch (IOException e) {
            callback.onError(e);
        }
    }

    private File createFile(String fileName) {
        return new File(location + File.separator + fileName);
    }
}
