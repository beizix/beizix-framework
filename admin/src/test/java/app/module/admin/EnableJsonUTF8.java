package app.module.admin;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.*;

/**
 * MockMvc `application/json` 타입 전송 시 UTF-8 설정 어노테이션
 *
 * @author Youngmok Kim
 * @version 1.0 2022-06-27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoConfigureMockMvc
@Import(EnableJsonUTF8.Config.class)
public @interface EnableJsonUTF8 {
  class Config {
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
      return new CharacterEncodingFilter("UTF-8", true);
    }
  }
}
