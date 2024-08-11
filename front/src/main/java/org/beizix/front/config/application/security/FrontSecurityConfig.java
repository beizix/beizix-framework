package org.beizix.front.config.application.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.application.enums.PublicAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FrontSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    List<String> permitPaths =
        Arrays.stream(PublicAccess.values())
            .map(PublicAccess::getPath)
            .collect(Collectors.toList());

    // 익명 사용자 접근 허용 URI 등록
    FrontPublicAccess.getInstance().setURIs(List.of("/"));

    http.authorizeHttpRequests(
            (auth) ->
                auth.antMatchers(permitPaths.toArray(String[]::new))
                    .permitAll()
                    // 익명 사용자 접근 허용 URI 적용.
                    .antMatchers(FrontPublicAccess.getInstance().getURIs().toArray(String[]::new))
                    .hasRole("ANONYMOUS")
                    .anyRequest()
                    .authenticated())
        .formLogin()
        .loginPage("/login")
        .permitAll();

    return http.build();
  }
}
