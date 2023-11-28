package org.beizix.front.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.web.xss.HTMLCharacterEscapes;
import org.beizix.front.config.adapter.web.interceptor.CurrentDeviceInterceptor;
import org.beizix.front.config.adapter.web.interceptor.URIAndRoleInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@RequiredArgsConstructor
public class FrontWebMvcConfig implements WebMvcConfigurer {
  @Value("${path.upload.public}")
  private String publicPath;

  private final URIAndRoleInterceptor uriAndRoleInterceptor;
  private final CurrentDeviceInterceptor currentDeviceInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(uriAndRoleInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/content-disposition/inline/public/**")
        .excludePathPatterns("/content-disposition/attachment/**")
        .excludePathPatterns("/refresh/cacheable/**")
        .excludePathPatterns("/api/**")
        .excludePathPatterns("/error")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/login")
        .excludePathPatterns("/logout");

    registry.addInterceptor(localeChangeInterceptor());

    registry
        .addInterceptor(deviceResolverHandlerInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/content-disposition/inline/public/**")
        .excludePathPatterns("/content-disposition/attachment/**")
        .excludePathPatterns("/refresh/cacheable/**")
        .excludePathPatterns("/error")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/login")
        .excludePathPatterns("/logout");
    registry
        .addInterceptor(currentDeviceInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/content-disposition/inline/public/**")
        .excludePathPatterns("/content-disposition/attachment/**")
        .excludePathPatterns("/refresh/cacheable/**")
        .excludePathPatterns("/error")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/login")
        .excludePathPatterns("/logout");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/content-disposition/inline/public/**")
        .addResourceLocations("file:///" + publicPath + "/")
        .setCachePeriod(20);
  }

  // spring-mobile-device: Interceptor
  @Bean
  public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
  }
  // spring-mobile-device: Argument Resolver
  @Bean
  public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
    return new DeviceHandlerMethodArgumentResolver();
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
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

  /** JSON 응답시 HTML Escape 처리를 위해 선언 */
  @Bean
  public HttpMessageConverter escapingConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

    MappingJackson2HttpMessageConverter escapingConverter =
        new MappingJackson2HttpMessageConverter();
    escapingConverter.setObjectMapper(objectMapper);

    return escapingConverter;
  }

  /** form-data 요청시 HTML Escape 처리를 위해 선언 */
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
