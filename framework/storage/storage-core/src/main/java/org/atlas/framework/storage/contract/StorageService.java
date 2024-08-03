package org.atlas.framework.storage.contract;

import org.atlas.shared.util.function.Callback;

import java.util.Map;

public interface StorageService {

    void upload(String fileName, byte[] fileContent, Map<String, String> metadata, Callback<Void> callback);

    void download(String fileName, Callback<byte[]> callback);

    void delete(String fileName, Callback<Void> callback);
}
