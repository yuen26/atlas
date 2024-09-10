package org.atlas.business.user.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.command.contract.Command;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerCommand implements Command<List<CustomerDto>> {

    private List<Integer> ids;
}
