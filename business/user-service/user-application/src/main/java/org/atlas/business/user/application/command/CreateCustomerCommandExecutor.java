package org.atlas.business.user.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.CreateCustomerCommand;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.user.CustomerCreatedEvent;
import org.atlas.framework.event.contract.user.model.CustomerData;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.security.PasswordUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreateCustomerCommandExecutor implements CommandExecutor<CreateCustomerCommand, Integer> {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Integer execute(CreateCustomerCommand command) throws Exception {
        userRepository.findByEmail(command.getEmail())
            .ifPresent(existedUser -> {
                throw new BusinessException(AppError.EMAIL_ALREADY_EXISTS);
            });

        User customer = newCustomer(command);
        userRepository.insert(customer);

        CustomerCreatedEvent event = newEvent(customer);
        applicationEventPublisher.publishEvent(event);

        return customer.getId();
    }

    private User newCustomer(CreateCustomerCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        user.setPassword(PasswordUtil.encode(command.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setCredit(BigDecimal.ZERO);
        return user;
    }

    private CustomerCreatedEvent newEvent(User user) {
        CustomerData customerData = new CustomerData();
        customerData.setId(user.getId());
        customerData.setUsername(user.getUsername());
        customerData.setEmail(user.getEmail());
        return new CustomerCreatedEvent(customerData);
    }
}
