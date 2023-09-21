package org.beizix.utility.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
@RequiredArgsConstructor
public class CommonUtil {
  private final MessageUtil messageUtil;

  /**
   * 문자열의 마지막 lastChar 를 제거 후 반환
   *
   * @param str 문자열
   * @param lastChar 제거할 마지막 문자
   * @return 마지막 문자가 제거된 문자열
   */
  public String removeLastChar(String str, String lastChar) {
    if (str == null) return "";
    return str.endsWith(lastChar) ? str.substring(0, str.length() - lastChar.length()) : str;
  }

  /**
   * Ajax 요청 여부 판단
   *
   * @param request HttpServletRequest
   * @return boolean
   */
  public boolean isAjaxRequest(HttpServletRequest request) {
    return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
  }

  public String getFileExtension(String filename) {
    return Optional.ofNullable(filename)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(filename.lastIndexOf(".") + 1).toLowerCase())
        .orElse(null);
  }

  public void setValidationFailRedirectAttributes(
      RedirectAttributes redirectAttributes, BindingResult bindingResult) {
    Optional.ofNullable(bindingResult)
        .filter(Errors::hasGlobalErrors)
        .filter(bs -> bs.getGlobalError() != null)
        .ifPresent(
            bs ->
                redirectAttributes.addFlashAttribute(
                    "operationMessage", messageUtil.getErrorMessage(bs.getGlobalError())));
  }

  public String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null) ip = request.getHeader("Proxy-Client-IP");
    if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
    if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");
    if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    if (ip == null) ip = request.getRemoteAddr();

    return ip;
  }

  public String getLoginUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null
        ? ((UserDetails) authentication.getPrincipal()).getUsername()
        : null;
  }

  public HttpServletRequest getRequest() {
    HttpServletRequest request = null;
    if (RequestContextHolder.getRequestAttributes() != null) {
      request =
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    return request;
  }

  public void addFlashAlertMessages(HttpSession session, String msg) {
    List<String> flashMessages =
        session.getAttribute("flashAlertMessages") != null
            ? (List<String>) session.getAttribute("flashAlertMessages")
            : new ArrayList<>();

    flashMessages.add(msg);
    session.setAttribute("flashAlertMessages", flashMessages);
  }
}
