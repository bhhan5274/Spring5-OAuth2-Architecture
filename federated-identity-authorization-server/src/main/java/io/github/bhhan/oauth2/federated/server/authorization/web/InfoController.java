package io.github.bhhan.oauth2.federated.server.authorization.web;

import io.github.bhhan.oauth2.federated.server.authorization.config.user.User;
import io.github.bhhan.oauth2.federated.server.authorization.config.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class InfoController {
    private final UserRepository userRepository;

    @GetMapping("/userinfo")
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
