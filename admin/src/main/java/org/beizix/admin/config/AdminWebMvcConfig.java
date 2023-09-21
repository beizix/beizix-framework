package org.beizix.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.beizix.admin.config.interceptor.URIAndRoleInterceptor;
import org.beizix.core.common.util.HTMLCharacterEscapes;

@Configuration
@RequiredArgsConstructor
public class AdminWebMvcConfig implements WebMvcConfigurer {
  @Value("${path.upload.public}")
  private String publicPath;

  private final URIAndRoleInterceptor uriAndRoleInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(uriAndRoleInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/content-disposition/inline/public/**")
        .excludePathPatterns("/content-disposition/attachment/**")
        .excludePathPatterns("/api/**")
        .excludePathPatterns("/refresh/cacheable/**")
        .excludePathPatterns("/error")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/login")
        .excludePathPatterns("/logout");

    registry.addInterceptor(localeChangeInterceptor());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/content-disposition/inline/public/**")
        .addResourceLocations("file:///" + publicPath + "/")
        .setCachePeriod(20);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(escapingConverter());
  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver slr = new CookieLocaleResolver();
    slr.setDefaultLocale(Locale.getDefault());
    slr.setCookieName("lang");
    return slr;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  /** @RequestBody 로 전달되는 JSON 요청시 XSS 방지를 위해 선언 */
  @Bean
  public HttpMessageConverter escapingConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

    MappingJackson2HttpMessageConverter escapingConverter =
        new MappingJackson2HttpMessageConverter();
    escapingConverter.setObjectMapper(objectMapper);

    return escapingConverter;
  }

  /** form-data 요청시 XSS 방지를 위해 선언 */
  @Bean
  public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
    FilterRegistrationBean<XssEscapeServletFilter> filterRegistration =
        new FilterRegistrationBean<>();
    filterRegistration.setFilter(new XssEscapeServletFilter());
    filterRegistration.setOrder(1);
    filterRegistration.addUrlPatterns("/*");

    return filterRegistration;
  }
}
