package org.atlas.customer.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.context.CurrentUser;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.customer.application.contract.command.GetProfileCommand;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.customer.domain.entity.Customer;
import org.atlas.customer.domain.repository.CustomerRepository;
import org.atlas.framework.command.executor.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProfileCommandExecutor implements CommandExecutor<GetProfileCommand, CustomerDto> {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto execute(GetProfileCommand command) throws Exception {
        CurrentUser currentUser = UserContext.getCurrentUser();
        Customer customer = customerRepository.findByUserId(currentUser.getUserId())
            .orElseThrow(() -> new BusinessException(AppError.CUSTOMER_NOT_FOUND));
        return ModelMapperUtil.map(customer, CustomerDto.class);
    }
}
