package io.github.bhhan.oauth2.federated.server.authorization.config.provider;

import io.github.bhhan.oauth2.federated.server.authorization.config.user.User;
import io.github.bhhan.oauth2.federated.server.authorization.config.user.UserPrincipal;
import io.github.bhhan.oauth2.federated.server.authorization.config.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }
        return UserPrincipal.create(user);
    }
}
