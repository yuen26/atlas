package org.atlas.customer.application.contract.command;

import lombok.Data;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.command.Command;

@Data
public class GetProfileCommand implements Command<CustomerDto> {
}
