package org.beizix.core.config.application.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoreBeans {
  @Bean
  @Description("model 객체간 mapping 프레임워크")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

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
