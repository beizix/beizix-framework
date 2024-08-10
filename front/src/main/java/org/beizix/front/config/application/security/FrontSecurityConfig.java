package org.beizix.front.config.application.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.front.config.application.security.enums.PublicAccess;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FrontSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    List<String> permitPaths =
        Arrays.stream(PublicAccess.values())
            .map(PublicAccess::getPath)
            .collect(Collectors.toList());

    permitPaths.add("/");

    http.authorizeRequests()
        .antMatchers(permitPaths.toArray(String[]::new))
        .permitAll()
        .anyRequest()
        .authenticated();
  }
}
