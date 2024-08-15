package app.module.core.config.application.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;

/** String 문자열을 LocalDateTime 으로 변환해주는 Converter. 현재 사용하지 않는다! */
@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
  @Override
  public LocalDateTime convert(String source) {
    return StringUtils.isEmpty(source) ? null : LocalDateTime.parse(source);
  }
}
