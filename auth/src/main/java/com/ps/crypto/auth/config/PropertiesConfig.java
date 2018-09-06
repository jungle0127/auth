package com.ps.crypto.auth.config;

import com.ps.crypto.auth.properties.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class PropertiesConfig {
}
