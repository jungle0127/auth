package com.westone.cryptoservice.lm.auth.authorize;

import com.westone.cryptoservice.lm.auth.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class CommonAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private AuthProperties authProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                authProperties.getLoginPage(),
                authProperties.getLoginProcessingUrl()
        ).permitAll();
    }
}
