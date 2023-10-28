package org.beizix.front.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.in.uri.URIHierarchyPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingParentsPortIn;
import org.beizix.core.application.port.in.uri.URIMatchingPortIn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class URIAndRoleInterceptor implements HandlerInterceptor {
  private final URIMatchingPortIn uriMatchingPortIn;
  private final URIMatchingParentsPortIn uriMatchingParentsPortIn;
  private final URIHierarchyPortIn uriHierarchyPortIn;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.startsWith("/error/interceptor")) {
      return true;
    }

    URIOutput currentURI = uriMatchingPortIn.connect(AppType.FRONT, requestURI);
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

    modelAndView.addObject("topNode", uriHierarchyPortIn.connect(AppType.FRONT));

    if (modelAndView.getModelMap().getAttribute("currentURI") == null) {
      modelAndView.addObject("currentURI", request.getAttribute("currentURI"));
    }

    modelAndView.addObject(
        "menuHierarchy", uriMatchingParentsPortIn.connect(AppType.FRONT, request.getRequestURI()));
  }
}
