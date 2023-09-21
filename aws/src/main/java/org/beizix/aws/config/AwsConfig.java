package org.beizix.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile(value = {"local", "dev", "prod"})
public class AwsConfig {
  @Value("${recycle.aws.s3.accessKey}")
  private String s3AccessKey;

  @Value("${recycle.aws.s3.secretKey}")
  private String s3SecretKey;

  @Value("${cloud.aws.region.static}")
  private String region;

  @Bean
  public AmazonS3Client amazonS3Client() {
    BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
    return (AmazonS3Client)
        AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
            .build();
  }
}
