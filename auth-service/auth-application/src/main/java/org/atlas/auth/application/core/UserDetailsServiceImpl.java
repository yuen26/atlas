package org.atlas.auth.application.core;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.entity.User;
import org.atlas.auth.domain.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(this::map)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public LoginInfo map(User user) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(user.getId());
        loginInfo.setUsername(user.getUsername());
        loginInfo.setPassword(user.getPassword());
        loginInfo.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        return loginInfo;
    }
}
