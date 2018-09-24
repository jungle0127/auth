package com.ps.saa.core.validate.filter.authenticationfilter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SMSAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SMSAuthenticationToken smsAuthenticationToken = (SMSAuthenticationToken) authentication;
        UserDetails userdetails = this.userDetailsService.loadUserByUsername(String.valueOf(smsAuthenticationToken.getPrincipal()));
        if(userdetails == null){
            throw new InternalAuthenticationServiceException("Can not find the phone number");
        }
        SMSAuthenticationToken authenticatedToken = new SMSAuthenticationToken(userdetails.getUsername(),userdetails.getAuthorities());
        authenticatedToken.setDetails(smsAuthenticationToken.getDetails());
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SMSAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
