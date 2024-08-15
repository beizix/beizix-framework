package org.beizix.front.config.application.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.application.enums.PublicAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FrontSecurityConfig {
  @Value("${app.front.session.maximum.num}")
  private Integer maxSessionNum;

  private final FrontDetailService frontDetailService;
  private final DataSource dataSource;
  private final BCryptPasswordEncoder passwordEncoder;

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    return jdbcTokenRepository;
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(frontDetailService);
    auth.setPasswordEncoder(passwordEncoder);
    return auth;
  }

  // 중복 로그인 방지를 위해 선언
  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  // 중복 로그인 방지를 위해 선언
  @Bean
  public ServletListenerRegistrationBean httpSessionEventPublisher() {
    return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    List<String> permitPaths =
        Arrays.stream(PublicAccess.values())
            .map(PublicAccess::getPath)
            .collect(Collectors.toList());

    // 익명 사용자 + 인증 사용자 접근 허용 URI 등록
    FrontPublicAccess.getInstance().setURIs(List.of("/"));

    http.authorizeHttpRequests(
            (auth) ->
                auth.antMatchers(permitPaths.toArray(String[]::new))
                    .permitAll()
                    // 익명 사용자 + 인증 사용자 접근 허용 URI 적용.
                    .antMatchers(FrontPublicAccess.getInstance().getURIs().toArray(String[]::new))
                    .hasAnyRole("ANONYMOUS", "GENERAL")
                    .anyRequest()
                    .authenticated())
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .rememberMe()
        .tokenValiditySeconds(1209600)
        .userDetailsService(frontDetailService)
        .tokenRepository(persistentTokenRepository())
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll()
        .and()
        .sessionManagement()
        .maximumSessions(maxSessionNum)
        .maxSessionsPreventsLogin(false);

    return http.build();
  }
}
