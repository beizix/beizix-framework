package org.beizix.core.config.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.beizix.core.config.application.exception.NoMatchingURIException;

import javax.servlet.http.HttpServletRequest;

/**
 * 인터셉터에서 던진 예외를 GlobalControllerAdvice 에서 처리하지 못한다. 인터셉터에서 예외 발생 시 /error/interceptor URL 로 포워딩 시켜
 * Controller 단에서 예외를 다시 발생시켜 GlobalControllerAdvice 가 처리할 수 있도록 한다. 참고 -
 * https://dev-monkey-dugi.tistory.com/136
 */
@Controller
public class InterceptorErrorController {
  @GetMapping("/error/interceptor")
  public void error(HttpServletRequest request) {
    String message = (String) request.getAttribute("message");
    String exception = (String) request.getAttribute("exception");

    switch (exception) {
      case "NoMatchingURIException":
        throw new NoMatchingURIException(message);
    }
  }
}
