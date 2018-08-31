package com.westone.cryptoservice.lm.auth.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westone.cryptoservice.lm.auth.dto.AuthResponse;
import com.westone.cryptoservice.lm.auth.properties.AuthProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("authenticationFailureHandler")
public class CommonAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.warn("Login failed.");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        AuthResponse<String> failureResponse = new AuthResponse<>(exception.getMessage());
        failureResponse.setMessage("Login failed.");
        response.getWriter().write(objectMapper.writeValueAsString(failureResponse));
    }
}
