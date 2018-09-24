package com.ps.saa.core.config;

import com.ps.saa.core.service.DefaultUserDetailsService;
import com.ps.saa.core.validate.code.sender.SMSCodeSender;
import com.ps.saa.core.validate.code.sender.impl.DefaultSMSCodeSenderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SAABeanConfig {
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
}
