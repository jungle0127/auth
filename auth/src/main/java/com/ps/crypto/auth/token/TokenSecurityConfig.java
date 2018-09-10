package com.ps.crypto.auth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class TokenSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        TokenFilter tokenFilter = new TokenFilter();
        tokenFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        TokenAuthenticationProvider tokenAuthenticationProvider = new TokenAuthenticationProvider();
        tokenAuthenticationProvider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(tokenAuthenticationProvider)
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("").permitAll();

    }
}
