package com.ps.saa.core.config.authenticate;

import com.ps.saa.core.validate.filter.authenticationfilter.SMSAuthenticationFilter;
import com.ps.saa.core.validate.filter.authenticationfilter.SMSAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SMSCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SMSAuthenticationFilter smsAuthenticationFilter = new SMSAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        SMSAuthenticationProvider smsAuthenticationProvider = new SMSAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        //remember me feature
        String rememberMeTokenKey = UUID.randomUUID().toString();
        smsAuthenticationFilter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(rememberMeTokenKey,userDetailsService,persistentTokenRepository));

        builder.authenticationProvider(smsAuthenticationProvider)
                .addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
