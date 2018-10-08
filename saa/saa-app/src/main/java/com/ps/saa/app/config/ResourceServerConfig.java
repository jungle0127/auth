package com.ps.saa.app.config;

import com.ps.saa.core.config.SMSCodeAuthenticationSecurityConfig;
import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.properties.SAAProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SMSCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SAAProperties saaProperties;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .loginPage(SAAConstants.DEFAULT_LOGIN_PAGE)
                .loginProcessingUrl(SAAConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL);
        http.apply(smsCodeAuthenticationSecurityConfig).and()
                .authorizeRequests()
                .antMatchers( SAAConstants.VALIDATE_CODE_SMS_URL,
                        SAAConstants.VALIDATE_CODE_IMAGE_URL,
                        SAAConstants.AUTHENTICATE_URL,
                        saaProperties.getBrowser().getLoginProcessingUrl(),
                        saaProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
