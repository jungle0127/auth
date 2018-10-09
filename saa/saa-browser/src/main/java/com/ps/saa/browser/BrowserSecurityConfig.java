package com.ps.saa.browser;

import com.ps.saa.core.authorize.ConfigureManager;
import com.ps.saa.core.config.authenticate.FormAuthenticationConfigure;
import com.ps.saa.core.config.authenticate.SMSCodeAuthenticationSecurityConfig;
import com.ps.saa.core.properties.SAAProperties;
import com.ps.saa.core.validate.filter.ImageCodeFilter;
import com.ps.saa.core.validate.filter.SMSCodeFilter;
import com.ps.saa.core.validate.filter.authenticationfilter.SMSAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FormAuthenticationConfigure formAuthenticationConfigure;
    @Autowired
    private SMSCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private ConfigureManager configureManager;
    @Autowired
    private PersistentTokenRepository tokenRepository;
    @Autowired
    private SAAProperties saaProperties;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfigure.config(http);// form login configuration
        configureManager.config(http.authorizeRequests()); // extensible configuration, include permitted URLs configuration.
        http.addFilterBefore(new ImageCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new SMSCodeFilter(),UsernamePasswordAuthenticationFilter.class);
        http.rememberMe()
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(saaProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}
