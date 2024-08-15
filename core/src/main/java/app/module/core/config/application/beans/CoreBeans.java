package app.module.core.config.application.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoreBeans {

  @Bean
  @Description("Jackson JSON object mapper")
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  @Description("Apache Tika - a content analysis toolkit")
  public Tika tika() {
    return new Tika();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
