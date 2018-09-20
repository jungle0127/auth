package com.ps.saa.core.config;

import com.ps.saa.core.validate.code.filter.authenticationfilter.SMSAuthenticationFilter;
import com.ps.saa.core.validate.code.filter.authenticationfilter.SMSAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SMSCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SMSAuthenticationFilter smsAuthenticationFilter = new SMSAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
//        smsAuthenticationFilter.setAuthenticationFailureHandler();
//        smsAuthenticationFilter.setAuthenticationSuccessHandler();
        SMSAuthenticationProvider smsAuthenticationProvider = new SMSAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        builder.authenticationProvider(smsAuthenticationProvider)
                .addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
