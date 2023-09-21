package org.beizix.aws;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
@PropertySources({
  @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "aws.properties"),
  @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "aws-${spring.profiles.active}.properties")
})
public class AwsApplication {}
