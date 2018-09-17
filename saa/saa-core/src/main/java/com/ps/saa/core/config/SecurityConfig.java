package com.ps.saa.core.config;

import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.properties.SAAProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SAAProperties saaProperties;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage(SAAConstants.AUTHENTICATE_URL)
                .loginProcessingUrl(this.saaProperties.getBrowser().getLoginProcessingUrl())
                .and()
                .authorizeRequests().antMatchers(
                        SAAConstants.AUTHENTICATE_URL,
                        saaProperties.getBrowser().getLoginProcessingUrl(),
                        saaProperties.getBrowser().getLoginPage()).permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
