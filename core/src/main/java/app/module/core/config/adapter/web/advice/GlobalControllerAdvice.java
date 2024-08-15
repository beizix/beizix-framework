package app.module.core.config.adapter.web.advice;

import app.module.core.config.application.exception.NoMatchingURIException;
import app.module.core.config.application.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 전역 예외 제어 객체.
 */
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerAdvice {
  private final CommonUtil commonUtil;

  @ExceptionHandler({RuntimeException.class})
  public Object runtimeException(RuntimeException e, Model model, HttpServletRequest request) {
    log.error(
        "[ERROR INFO] - '{}' {}",
        request.getMethod(),
        request.getRequestURL()
            + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));
    log.error("[ERROR DETAIL]", e);

    if (commonUtil.isAjaxRequest(request)) {
      // ajax 요청일 경우
      return getAjaxExceptionBody(e);
    } else {
      // 일반 요청일 경우
      model.addAttribute("error", e.getClass().getSimpleName());
      model.addAttribute("message", e.getMessage());

      return "error/500";
    }
  }

  @ExceptionHandler({NoMatchingURIException.class})
  public Object noMatchingURI(NoMatchingURIException e, Model model) {
    model.addAttribute("error", "NoMatchingURIException");
    model.addAttribute("message", e.getMessage());

    log.error("NoMatchingURIException - {}", e.getMessage());

    return "error/404";
  }

  @ExceptionHandler({AccessDeniedException.class})
  public Object runtimeException(AccessDeniedException e, Model model) {
    model.addAttribute("error", e.getClass().getSimpleName());
    model.addAttribute("message", e.getMessage());

    Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .ifPresent(
            authentication -> {
              model.addAttribute("name", authentication.getName());
              model.addAttribute("authorities", authentication.getAuthorities());
            });

    log.error("[error]", e);

    return "error/403";
  }

  @ResponseBody
  public ResponseEntity<GlobalRestExceptionVO> getAjaxExceptionBody(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            GlobalRestExceptionVO.builder()
                .message(e.getClass().getSimpleName() + " - " + e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build());
  }
}
