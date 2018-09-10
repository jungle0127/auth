package com.ps.crypto.auth.token;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpTokenAuthenticationToken authenticationToken = (HttpTokenAuthenticationToken)authentication;
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(String.valueOf(authenticationToken.getPrincipal()));
        if(userDetails == null){
            throw new InternalAuthenticationServiceException("Can not find the user's information.");
        }

        HttpTokenAuthenticationToken authenticatedToken = new HttpTokenAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticatedToken.setDetails(authenticationToken.getDetails());
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HttpTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
