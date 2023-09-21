package org.beizix.core.config.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.beizix.core.config.exception.NoMatchingURIException;

import javax.servlet.http.HttpServletRequest;

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
