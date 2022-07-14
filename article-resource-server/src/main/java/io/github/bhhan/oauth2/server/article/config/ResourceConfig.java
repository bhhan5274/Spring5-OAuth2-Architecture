package io.github.bhhan.oauth2.server.article.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/v1/articles/**")
                .hasAuthority("SCOPE_message.read")
                .antMatchers(HttpMethod.POST, "/v1/articles/**")
                .hasAuthority("SCOPE_message.write")
                .antMatchers(HttpMethod.DELETE, "/v1/articles/**")
                .hasAuthority("SCOPE_message.write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
