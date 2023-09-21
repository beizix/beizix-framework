package org.beizix.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "org.beizix")
/*
 * specification-with-projection - https://github.com/pramoth/specification-with-projection
 */
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class FrontApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(FrontApplication.class, args);
  }

  /**
   * maven jar 배포시 필요없지만, war 배포를 선택한 경우 configure 오버라이드가 필요하기에 선언.
   *
   * @param builder
   * @return
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(FrontApplication.class);
  }
}
