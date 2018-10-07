package com.ps.saa.authorize;

import com.ps.saa.core.authorize.ConfigureProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class BrowserConfigureProvider implements ConfigureProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET,
                "/**/*.js","/**/*.css",
                "/**/*.png","/**/*.gif", "/**/*.jpg")
                .permitAll();
    }
}
