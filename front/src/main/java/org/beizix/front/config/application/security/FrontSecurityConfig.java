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

    // 자격증명 SKIP 허용 URI 등록
    FrontPublicAccess.getInstance().setURIs(List.of("/"));

    permitPaths.addAll(FrontPublicAccess.getInstance().getURIs());

    http.authorizeRequests(
        (req) ->
            req.antMatchers(permitPaths.toArray(String[]::new))
                .permitAll()
                .anyRequest()
                .authenticated());

    return http.build();
  }
}
