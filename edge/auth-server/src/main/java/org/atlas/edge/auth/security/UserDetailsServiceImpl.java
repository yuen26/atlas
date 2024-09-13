package org.atlas.edge.auth.security;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.api.client.contract.user.IUserAuthServiceClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserAuthServiceClient userClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userClient.getUserAuth(email)
            .map(this::map)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    public LoginInfo map(UserAuthDto userAuthDto) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(userAuthDto.getEmail());
        loginInfo.setPassword(userAuthDto.getPassword());
        loginInfo.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(userAuthDto.getRole().name())));
        loginInfo.setUserId(userAuthDto.getId());
        return loginInfo;
    }
}
