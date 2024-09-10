package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderCommand implements Command<Void> {

    @NotNull
    private FileType fileType;

    @NotEmpty
    private byte[] fileContent;
}
