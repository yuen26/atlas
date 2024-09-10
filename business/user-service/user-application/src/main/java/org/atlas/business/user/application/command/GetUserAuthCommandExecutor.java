package org.atlas.business.user.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.GetUserAuthCommand;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.business.user.domain.entity.User;
import org.atlas.business.user.domain.repository.UserRepository;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.command.contract.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserAuthCommandExecutor implements CommandExecutor<GetUserAuthCommand, UserAuthDto> {

    private final UserRepository userRepository;

    @Override
    public UserAuthDto execute(GetUserAuthCommand command) throws Exception {
        User user = userRepository.findByEmail(command.getEmail())
            .orElseThrow(() -> new BusinessException(AppError.USER_NOT_FOUND));
        return ModelMapperUtil.map(user, UserAuthDto.class);
    }
}
