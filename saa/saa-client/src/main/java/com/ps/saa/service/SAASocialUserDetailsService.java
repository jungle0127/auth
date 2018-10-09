package com.ps.saa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SAASocialUserDetailsService implements SocialUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("lotus");
        return new SocialUser(userId,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
