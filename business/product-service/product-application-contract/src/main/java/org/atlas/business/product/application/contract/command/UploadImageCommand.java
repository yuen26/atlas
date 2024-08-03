package org.atlas.business.product.application.contract.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageCommand implements Command<Void> {

    @NotNull
    private Integer productId;

    @NotEmpty
    private byte[] fileContent;
}
