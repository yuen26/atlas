package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.application.contract.command.UploadImageCommand;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.storage.contract.StorageService;
import org.atlas.shared.util.function.Callback;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UploadImageCommandExecutor implements CommandExecutor<UploadImageCommand, Void> {

    private final StorageService storageService;

    @Override
    public Void execute(UploadImageCommand request) throws Exception {
        storageService.upload(String.valueOf(request.getProductId()), request.getFileContent(), null, new Callback<>() {
            @Override
            public void onSuccess(Void result) {
                log.info("Uploaded image successfully: productId={}", request.getProductId());
            }

            @Override
            public void onError(Exception e) {
                log.error("Failed to upload image: productId={}", request.getProductId(), e);
            }
        });
        return null;
    }
}
