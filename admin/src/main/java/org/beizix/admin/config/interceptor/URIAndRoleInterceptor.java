package org.beizix.admin.config.interceptor;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.config.interceptor.model.URITopTierVO;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.application.domain.uri.model.toptier.URITopTierOutput;
import org.beizix.core.application.port.in.uri.URIMatchingParentsPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingPortIn;
import org.beizix.core.application.port.in.uri.URITopTierPortIn;
import org.beizix.core.config.enums.AppType;
import org.beizix.utility.common.CommonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class URIAndRoleInterceptor implements HandlerInterceptor {
  private final CommonUtil commonUtil;
  private final URIMatchingPortIn uriMatchingPortIn;
  private final URIMatchingParentsPortIn uriMatchingParentsPortIn;
  private final URITopTierPortIn topTierPortIn;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith("/error/interceptor")) {
      return true;
    }

    URIViewOutput currentURI = uriMatchingPortIn.connect(AppType.ADMIN, requestURI);
    if (currentURI == null) {
      request.setAttribute("message", String.format("매핑되는 않은 URI - %s", requestURI));
      request.setAttribute("exception", "NoMatchingURIException");
      request.getRequestDispatcher("/error/interceptor").forward(request, response);
      return false;
    }

    request.setAttribute("currentURI", currentURI);

    // Home url 인 경우 권한체크 없음.
    if (currentURI.getUri().equals("/")) {
      return true;
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // ROLE_SUPER 권한이 없다면 URI 에 매핑된 권한체크 수행
    if (auth.getAuthorities().stream()
        .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SUPER"))) {

      Set<String> currentUriRoles = currentURI.getRoles();

      auth.getAuthorities().stream()
          .filter(grantedAuthority -> currentUriRoles.contains(grantedAuthority.getAuthority()))
          .findFirst()
          .orElseThrow(
              () ->
                  new AccessDeniedException(
                      String.format(
                          "[AccessDenied] %s to %s", auth.getName(), currentURI.getUri())));
    }

    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {

    if (modelAndView == null) return;

    if (!commonUtil.isAjaxRequest(request)) {
      URITopTierOutput topTierOutput = topTierPortIn.connect(AppType.ADMIN);
      modelAndView.addObject("topNode", recursiveMapping(topTierOutput));

      if (modelAndView.getModelMap().getAttribute("currentURI") == null) {
        modelAndView.addObject("currentURI", request.getAttribute("currentURI"));
      }

      modelAndView.addObject(
          "menuHierarchy",
          uriMatchingParentsPortIn.connect(AppType.ADMIN, request.getRequestURI()));
    }
  }

  private URITopTierVO recursiveMapping(URITopTierOutput output) {
    return new URITopTierVO(
        output.getId(),
        output.getText(),
        output.getUri(),
        output.getShowOnNavi(),
        CollectionUtils.emptyIfNull(output.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
