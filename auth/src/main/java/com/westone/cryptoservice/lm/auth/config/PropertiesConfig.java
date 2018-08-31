package com.westone.cryptoservice.lm.auth.config;

import com.westone.cryptoservice.lm.auth.properties.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class PropertiesConfig {
}
