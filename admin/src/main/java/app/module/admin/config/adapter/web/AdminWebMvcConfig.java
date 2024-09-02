package app.module.admin.config.adapter.web;

import app.module.admin.config.adapter.web.interceptor.AdminURIInterceptor;
import app.module.core.config.adapter.web.interceptor.URIAuthorizeInterceptor;
import app.module.core.config.application.enums.PublicAccess;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@RequiredArgsConstructor
public class AdminWebMvcConfig implements WebMvcConfigurer {
  @Value("${path.upload.public}")
  private String publicPath;

  private final AdminURIInterceptor adminURIInterceptor;
  private final URIAuthorizeInterceptor uriAuthorizeInterceptor;
  private final HttpMessageConverter httpMessageConverter;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(adminURIInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()))
        .excludePathPatterns("/api/**"); // api 는 menu 정보가 없기에 skip

    registry
        .addInterceptor(uriAuthorizeInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            Arrays.stream(PublicAccess.values())
                .map(PublicAccess::getPath)
                .collect(Collectors.toList()))
        .excludePathPatterns("/api/**"); // api 는 menu 정보가 없기에 skip

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
    converters.add(httpMessageConverter);
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
}
