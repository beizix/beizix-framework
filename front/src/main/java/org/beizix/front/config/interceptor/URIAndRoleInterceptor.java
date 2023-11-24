package org.beizix.front.config.interceptor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.currentmatch.domain.URICurrentMatching;
import org.beizix.core.usecase.uri.ancestry.application.port.in.URIAncestryPortIn;
import org.beizix.core.usecase.uri.currentmatch.application.port.in.URICurrentMatchingPortIn;
import org.beizix.core.config.enums.AppType;
import org.beizix.front.usecase.uri.toptier.port.in.URITopTierPortIn;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class URIAndRoleInterceptor implements HandlerInterceptor {
  private final URICurrentMatchingPortIn URICurrentMatchingPortIn;
  private final URIAncestryPortIn uriAncestryPortIn;
  private final URITopTierPortIn uriTopTierPortIn;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith("/error/interceptor")) {
      return true;
    }

    URICurrentMatching currentURI = URICurrentMatchingPortIn.connect(AppType.FRONT, requestURI);
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

    modelAndView.addObject("topNode", uriTopTierPortIn.connect(AppType.FRONT));

    if (modelAndView.getModelMap().getAttribute("currentURI") == null) {
      modelAndView.addObject("currentURI", request.getAttribute("currentURI"));
    }

    modelAndView.addObject(
        "menuHierarchy", uriAncestryPortIn.connect(AppType.FRONT, request.getRequestURI()));
  }
}
