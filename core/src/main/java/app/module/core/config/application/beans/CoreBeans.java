package app.module.core.config.application.beans;

import app.module.core.config.adapter.web.xss.HTMLCharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class CoreBeans {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    // 자바 LocalDateTime 타입을 인식시키기 위해 선언.
    objectMapper.registerModule(new JavaTimeModule());
    // LocalDateTime 타입을 JSON 으로 직렬화하는 과정에서 ISO string 타입으로 변환하기 위한 선언.
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    return objectMapper;
  }

  /** content-type: application/json 으로 전달되는 요청값의 XSS 방지를 위해 선언 */
  @Bean
  public HttpMessageConverter httpMessageConverter() {
    ObjectMapper objectMapper = objectMapper();
    objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

    MappingJackson2HttpMessageConverter escapingConverter =
        new MappingJackson2HttpMessageConverter();
    escapingConverter.setObjectMapper(objectMapper);

    return escapingConverter;
  }

  /** content-type: application/x-www-form-urlencoded 으로 전달되는 요청값의 XSS 방지를 위해 선언 */
  @Bean
  public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
    FilterRegistrationBean<XssEscapeServletFilter> filterRegistration =
        new FilterRegistrationBean<>();
    filterRegistration.setFilter(new XssEscapeServletFilter());
    filterRegistration.setOrder(1);
    filterRegistration.addUrlPatterns("/*");

    return filterRegistration;
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
