package com.ps.saa.core.authorize.impl;

import com.ps.saa.core.authorize.ConfigureManager;
import com.ps.saa.core.authorize.ConfigureProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigureManagerImpl implements ConfigureManager {
    @Autowired
    private List<ConfigureProvider> configureProviderList;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for(ConfigureProvider configureProvider: configureProviderList){
            configureProvider.config(config);
        }
        config.anyRequest().authenticated();
    }
}
