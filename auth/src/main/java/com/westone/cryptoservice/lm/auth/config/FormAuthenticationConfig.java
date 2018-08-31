package com.westone.cryptoservice.lm.auth.config;

import com.westone.cryptoservice.lm.auth.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationConfig {
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthProperties authProperties;
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(authProperties.getLoginPage())
                .loginProcessingUrl(authProperties.getLoginProcessingUrl())
                .failureHandler(authenticationFailureHandler);
    }
}
