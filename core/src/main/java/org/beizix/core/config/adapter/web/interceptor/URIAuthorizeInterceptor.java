package org.beizix.core.config.adapter.web.interceptor;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class URIAuthorizeInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {

    // 이전 UriInterceptor 로 부터 전달받은 값.
    URICurrentMatching currentURI = (URICurrentMatching) request.getAttribute("currentURI");

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    Set<String> currentUriRoles = currentURI.getRoles();

    auth.getAuthorities().stream()
        .filter(grantedAuthority -> currentUriRoles.contains(grantedAuthority.getAuthority()))
        .findFirst()
        .orElseThrow(
            () ->
                new AccessDeniedException(
                    String.format("[AccessDenied] %s to %s", auth.getName(), currentURI.getUri())));

    return true;
  }
}
