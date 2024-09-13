package org.atlas.business.user.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.ListCustomerCommand;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListCustomerCommandExecutor implements CommandExecutor<ListCustomerCommand, List<CustomerDto>> {

    private final UserRepository userRepository;

    @Override
    public List<CustomerDto> execute(ListCustomerCommand request) throws Exception {
        return userRepository.findByIdIn(request.getIds())
            .stream()
            .filter(user -> Role.CUSTOMER.equals(user.getRole()))
            .map(user -> ModelMapperUtil.map(user, CustomerDto.class))
            .toList();
    }
}
