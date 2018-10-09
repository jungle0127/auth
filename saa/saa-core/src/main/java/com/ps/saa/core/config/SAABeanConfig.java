package com.ps.saa.core.config;

import com.ps.saa.core.handler.DefaultAuthenticationFailureHandler;
import com.ps.saa.core.handler.DefaultAuthenticationSuccessHandler;
import com.ps.saa.core.service.DefaultUserDetailsService;
import com.ps.saa.core.validate.code.sender.SMSCodeSender;
import com.ps.saa.core.validate.code.sender.impl.DefaultSMSCodeSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SAABeanConfig {
    @Qualifier("dataSource")
    @Autowired
    public DataSource dataSource;
    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new DefaultAuthenticationSuccessHandler();
    }
    @Bean
    @ConditionalOnMissingBean(AuthenticationFailureHandler.class)
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new DefaultAuthenticationFailureHandler();
    }
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService(){
        return new DefaultUserDetailsService();
    }
    @Bean
    @ConditionalOnMissingBean(SMSCodeSender.class)
    public SMSCodeSender smsCodeSender(){
        return new DefaultSMSCodeSenderImpl();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
}
