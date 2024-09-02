package app.module.front.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.web.interceptor.URIAuthorizeInterceptor;
import app.module.core.config.adapter.web.xss.HTMLCharacterEscapes;
import app.module.core.config.application.enums.PublicAccess;
import app.module.front.config.adapter.web.interceptor.CurrentDeviceInterceptor;
import app.module.front.config.adapter.web.interceptor.FrontURIInterceptor;
import app.module.front.config.application.security.FrontPublicAccess;
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

  private final FrontURIInterceptor frontURIInterceptor;
  private final URIAuthorizeInterceptor uriAuthorizeInterceptor;
  private final CurrentDeviceInterceptor currentDeviceInterceptor;
  private final ObjectMapper objectMapper;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(frontURIInterceptor)
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                // 로그인 페이지의 경우, currentURI 정보를 얻기 위해 실행되야 한다.
                .filter(publicAccess -> !publicAccess.getPath().equals("/login"))
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()));

    registry
        .addInterceptor(uriAuthorizeInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()))
        // 익명 사용자 + 인증 사용자 접근 허용 URI 는 인증체크 대상에서 제외
        .excludePathPatterns(FrontPublicAccess.getInstance().getURIs());

    registry.addInterceptor(localeChangeInterceptor());

    registry
        .addInterceptor(deviceResolverHandlerInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()));

    registry
        .addInterceptor(currentDeviceInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()));
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

  /**
   * content-type: application/json 으로 전달되는 요청값의 XSS 방지를 위해 선언
   */
  @Bean
  public HttpMessageConverter escapingConverter() {
    objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

    MappingJackson2HttpMessageConverter escapingConverter =
        new MappingJackson2HttpMessageConverter();
    escapingConverter.setObjectMapper(objectMapper);

    return escapingConverter;
  }

  /**
   * content-type: application/x-www-form-urlencoded 으로 전달되는 요청값의 XSS 방지를 위해 선언
   */
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
