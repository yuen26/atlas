package org.atlas.customer.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.command.Command;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerCommand implements Command<List<CustomerDto>> {

    private List<Integer> userIds;
}
