package com.ps.saa.browser;

import com.ps.saa.core.authorize.ConfigureManager;
import com.ps.saa.core.config.authenticate.FormAuthenticationConfigure;
import com.ps.saa.core.config.authenticate.SMSCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FormAuthenticationConfigure formAuthenticationConfigure;
    @Autowired
    private SMSCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private ConfigureManager configureManager;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfigure.config(http);
        http.apply(smsCodeAuthenticationSecurityConfig).and().csrf().disable();
        configureManager.config(http.authorizeRequests());
    }
}
