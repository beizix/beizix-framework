package org.beizix.admin.config.adapter.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.config.adapter.web.interceptor.model.URITopTierVO;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.util.CommonUtil;
import org.beizix.core.usecase.uri.ancestry.application.port.in.URIAncestryPortIn;
import org.beizix.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;
import org.beizix.core.usecase.uri.currentmatch.application.port.in.URICurrentMatchingPortIn;
import org.beizix.core.usecase.uri.toptier.application.domain.URITopTier;
import org.beizix.core.usecase.uri.toptier.application.port.in.URITopTierPortIn;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class URIInterceptor implements HandlerInterceptor {
  private final CommonUtil commonUtil;
  private final URICurrentMatchingPortIn uriCurrentMatchingPortIn;
  private final URIAncestryPortIn uriAncestryPortIn;
  private final URITopTierPortIn topTierPortIn;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestURI = request.getRequestURI();

    URICurrentMatching currentURI = uriCurrentMatchingPortIn.connect(AppType.ADMIN, requestURI);
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
      ModelAndView modelAndView)
      throws Exception {

    if (modelAndView == null) {
      log.info("POST_HANDLE_END::since modelAndView is null");
      return;
    }

    if (!commonUtil.isAjaxRequest(request)) {
      URITopTier topTierOutput = topTierPortIn.connect(AppType.ADMIN);
      modelAndView.addObject("topNode", recursiveMapping(topTierOutput));

      if (modelAndView.getModelMap().getAttribute("currentURI") == null) {
        modelAndView.addObject("currentURI", request.getAttribute("currentURI"));
      }

      modelAndView.addObject(
          "menuHierarchy", uriAncestryPortIn.connect(AppType.ADMIN, request.getRequestURI()));
    }
  }

  private URITopTierVO recursiveMapping(URITopTier output) {
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
