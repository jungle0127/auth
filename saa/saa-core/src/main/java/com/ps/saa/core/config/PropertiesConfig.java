package com.ps.saa.core.config;

import com.ps.saa.core.properties.SAAProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SAAProperties.class)
public class PropertiesConfig {
}
