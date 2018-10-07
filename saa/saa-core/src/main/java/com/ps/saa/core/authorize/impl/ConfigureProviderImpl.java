package com.ps.saa.core.authorize.impl;

import com.ps.saa.core.authorize.ConfigureProvider;
import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.properties.SAAProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class ConfigureProviderImpl implements ConfigureProvider {
    @Autowired
    private SAAProperties saaProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SAAConstants.VALIDATE_CODE_SMS_URL,
                SAAConstants.VALIDATE_CODE_IMAGE_URL,
                SAAConstants.AUTHENTICATE_URL,
                saaProperties.getBrowser().getLoginProcessingUrl(),
                saaProperties.getBrowser().getLoginPage())
               .permitAll();
    }
}
