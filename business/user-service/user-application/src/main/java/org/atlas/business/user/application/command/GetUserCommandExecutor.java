package org.atlas.business.user.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.GetUserCommand;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserCommandExecutor implements CommandExecutor<GetUserCommand, UserDto> {

    private final UserRepository userRepository;

    @Override
    public UserDto execute(GetUserCommand request) throws Exception {
        return userRepository.findById(request.getId())
            .map(user -> ModelMapperUtil.map(user, UserDto.class))
            .orElseThrow(() -> new BusinessException(AppError.USER_NOT_FOUND));
    }
}
