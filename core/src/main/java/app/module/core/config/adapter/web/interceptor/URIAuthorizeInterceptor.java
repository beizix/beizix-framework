package app.module.core.config.adapter.web.interceptor;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class URIAuthorizeInterceptor implements HandlerInterceptor {

  /**
   * 인증 받은 사용자를 대상으로 요청 리소스에 접근가능한 역할(+권한)인지를 검증한다. 인증을 받지 않은 익명 사용자의 경우 해당 인터셉터는 동작하지 않는다.
   *
   * @param request
   * @param response
   * @param handler
   * @return
   */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

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
                    String.format(
                        "[URIAuthorizeInterceptor - AccessDenied] %s to %s",
                        auth.getName(), currentURI.getUri())));

    return true;
  }
}
