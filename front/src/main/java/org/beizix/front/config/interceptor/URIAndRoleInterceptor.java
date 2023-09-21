package org.beizix.front.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIHierarchyService;
import org.beizix.core.feature.uri.application.service.URIMatchingParentsService;
import org.beizix.core.feature.uri.application.service.URIMatchingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class URIAndRoleInterceptor implements HandlerInterceptor {
  private final URIMatchingService uriMatchingService;
  private final URIMatchingParentsService uriMatchingParentsService;
  private final URIHierarchyService uriHierarchyService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith("/error/interceptor")) {
      return true;
    }

    URI currentURI = uriMatchingService.operate(AppType.FRONT, requestURI);
    if (currentURI == null) {
      request.setAttribute("message", String.format("매핑되는 않은 URI - %s", requestURI));
      request.setAttribute("exception", "NoMatchingURIException");
      request.getRequestDispatcher("/error/interceptor").forward(request, response);
      return false;
    }

    request.setAttribute("currentURI", currentURI);
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {

    if (modelAndView == null) return;

    modelAndView.addObject("topNode", uriHierarchyService.operate(AppType.FRONT));

    if (modelAndView.getModelMap().getAttribute("currentURI") == null) {
      modelAndView.addObject("currentURI", request.getAttribute("currentURI"));
    }

    modelAndView.addObject(
        "menuHierarchy", uriMatchingParentsService.operate(AppType.FRONT, request.getRequestURI()));
  }
}
