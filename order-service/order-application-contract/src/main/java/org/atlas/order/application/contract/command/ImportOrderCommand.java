package org.atlas.order.application.contract.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.Command;
import org.atlas.order.domain.shared.enums.FileType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderCommand implements Command<Void> {

    @NotNull
    private FileType fileType;

    @NotEmpty
    private byte[] fileContent;
}
