package org.beizix.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.beizix.aws.AwsApplication;
import org.beizix.core.CoreApplication;

@Configuration
@ComponentScan(basePackages = "org.beizix")
public class AdminCoreConfiguration {}
