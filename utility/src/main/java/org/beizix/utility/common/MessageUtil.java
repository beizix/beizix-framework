package org.beizix.utility.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
public class MessageUtil {
  private final MessageSource messageSource;

  public String getMessage(String code, Object... args) {
    String message = "";
    try {
      message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException ignored) {
    }
    return message;
  }

  public String getErrorMessage(ObjectError error) {
    return !StringUtils.isEmpty(error.getDefaultMessage())
        ? error.getDefaultMessage()
        : getMessage(error.getCode(), error.getArguments());
  }
}
