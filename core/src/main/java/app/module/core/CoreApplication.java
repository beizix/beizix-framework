package app.module.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@SpringBootApplication
@EnableCaching
@PropertySources({
  @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "core.properties"),
  @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "core-${spring.profiles.active}.properties")
})
/*
 * specification-with-projection - https://github.com/pramoth/specification-with-projection
 */
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class CoreApplication {}
