package org.atlas.framework.storage.firebase.service;

import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.atlas.framework.storage.contract.StorageService;
import org.atlas.shared.util.function.Callback;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FirebaseStorageService implements StorageService {

    private final StorageClient storageClient;

    @Override
    public void upload(String fileName, byte[] fileContent, Map<String, String> metadata, Callback<Void> callback) {
        try {
            storageClient.bucket()
                .create(fileName, fileContent);
            callback.onSuccess(null);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void download(String fileName, Callback<byte[]> callback) {
        try {
            Blob blob = getBlob(fileName);
            if (blob == null) {
                throw new IOException("Blob not found");
            }
            byte[] fileContent = blob.getContent();
            callback.onSuccess(fileContent);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void delete(String fileName, Callback<Void> callback) {
        try {
            Blob blob = getBlob(fileName);
            if (blob == null) {
                throw new IOException("Blob not found");
            }
            blob.delete();
            callback.onSuccess(null);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    private Blob getBlob(String fileName) {
        return storageClient.bucket().get(fileName);
    }
}
