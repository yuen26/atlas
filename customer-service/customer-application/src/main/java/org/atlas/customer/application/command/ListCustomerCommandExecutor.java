package org.atlas.customer.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.customer.application.contract.command.ListCustomerCommand;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.command.executor.CommandExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListCustomerCommandExecutor implements CommandExecutor<ListCustomerCommand, List<CustomerDto>> {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> execute(ListCustomerCommand request) throws Exception {
        return customerRepository.findByUserIdIn(request.getUserIds())
            .stream()
            .map(customer -> ModelMapperUtil.map(customer, CustomerDto.class))
            .toList();
    }
}
