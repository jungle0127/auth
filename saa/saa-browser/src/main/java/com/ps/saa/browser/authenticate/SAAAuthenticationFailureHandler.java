package com.ps.saa.browser.authenticate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.saa.core.dto.RestResponse;
import com.ps.saa.core.properties.ResponseType;
import com.ps.saa.core.properties.SAAProperties;
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
public class SAAAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SAAProperties saaProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.warn("Login failed.");
        if(ResponseType.JSON.equals(saaProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new RestResponse<String>(exception.getMessage())));
        } else{
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
