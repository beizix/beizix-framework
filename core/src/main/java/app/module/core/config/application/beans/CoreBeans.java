package app.module.core.config.application.beans;

import app.module.core.config.application.converter.SortJsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoreBeans {

  @Bean
  @Description("Jackson JSON object mapper")
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    // 자바 LocalDateTime 타입을 인식시키기 위해 선언.
    objectMapper.registerModule(new JavaTimeModule());
    // LocalDateTime 타입을 JSON 으로 직렬화하는 과정에서 ISO string 타입으로 변환하기 위한 선언.
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return objectMapper;
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
