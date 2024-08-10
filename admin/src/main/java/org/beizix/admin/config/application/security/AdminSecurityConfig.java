package org.beizix.admin.config.application.security;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.application.enums.PublicAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AdminSecurityConfig {

  @Value("${org.beizix.session.maximum.num}")
  private Integer maxSessionNum;

  private final AdminDetailsService adminDetailsService;
  private final DataSource dataSource;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AdminAuthSuccessHandler adminAuthSuccessHandler;
  private final AdminAuthFailHandler adminAuthFailHandler;

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(adminDetailsService);
    auth.setPasswordEncoder(passwordEncoder);
    return auth;
  }

  //  @Override
  //  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //    auth.authenticationProvider(authenticationProvider());
  //  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    return jdbcTokenRepository;
  }

  /** 중복 로그인 방지를 위해 선언 */
  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  /** 중복 로그인 방지를 위해 선언 */
  @Bean
  public ServletListenerRegistrationBean httpSessionEventPublisher() {
    return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
  }

  /** 인증 성공/실패 이벤트 수신을 위해 선언 */
  @Bean
  public AuthenticationEventPublisher authenticationEventPublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    List<String> permitPaths =
        Arrays.stream(PublicAccess.values())
            .map(PublicAccess::getPath)
            .collect(Collectors.toList());

    http.authorizeRequests()
        .antMatchers(permitPaths.toArray(String[]::new))
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .successHandler(adminAuthSuccessHandler)
        .failureHandler(adminAuthFailHandler)
        .permitAll()
        .and()
        .rememberMe()
        .tokenValiditySeconds(1209600)
        .userDetailsService(adminDetailsService)
        .tokenRepository(persistentTokenRepository())
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .logoutSuccessUrl("/login?logout")
        .permitAll()
        .and()
        .sessionManagement()
        .maximumSessions(maxSessionNum)
        .maxSessionsPreventsLogin(false);

    return http.build();
  }
}
