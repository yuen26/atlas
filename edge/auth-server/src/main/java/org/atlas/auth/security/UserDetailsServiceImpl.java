package org.atlas.auth.security;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserServiceClient userClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userClient.getLoginUser(email)
            .map(this::map)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    public LoginInfo map(LoginUserDto loginUserDto) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(loginUserDto.getEmail());
        loginInfo.setPassword(loginUserDto.getPassword());
        loginInfo.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(loginUserDto.getRole().name())));
        loginInfo.setUserId(loginUserDto.getId());
        return loginInfo;
    }
}
