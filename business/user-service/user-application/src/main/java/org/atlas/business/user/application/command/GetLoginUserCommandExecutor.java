package org.atlas.business.user.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.GetLoginUserCommand;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetLoginUserCommandExecutor implements CommandExecutor<GetLoginUserCommand, LoginUserDto> {

    private final UserRepository userRepository;

    @Override
    public LoginUserDto execute(GetLoginUserCommand command) throws Exception {
        User user = userRepository.findByEmail(command.getEmail())
            .orElseThrow(() -> new BusinessException(AppError.USER_NOT_FOUND));
        return ModelMapperUtil.map(user, LoginUserDto.class);
    }
}
