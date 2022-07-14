package io.github.bhhan.oauth2.federated.server.authorization.config;

import io.github.bhhan.oauth2.federated.server.authorization.config.provider.CustomOAuth2UserService;
import io.github.bhhan.oauth2.federated.server.authorization.config.provider.CustomUserDetailsService;
import io.github.bhhan.oauth2.federated.server.authorization.config.security.FederatedIdentityConfigurer;
import io.github.bhhan.oauth2.federated.server.authorization.config.security.UserRepositoryOAuth2UserHandler;
import io.github.bhhan.oauth2.federated.server.authorization.config.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, UserRepository userRepository) throws Exception {
        FederatedIdentityConfigurer federatedIdentityConfigurer = new FederatedIdentityConfigurer()
                .oauth2UserHandler(new UserRepositoryOAuth2UserHandler())
                .defaultOAuth2UserService(new CustomOAuth2UserService(userRepository));
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .mvcMatchers("/assets/**", "/webjars/**", "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .apply(federatedIdentityConfigurer)
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
