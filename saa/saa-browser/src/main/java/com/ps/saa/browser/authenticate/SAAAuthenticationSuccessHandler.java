package com.ps.saa.browser.authenticate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.saa.core.dto.RestResponse;
import com.ps.saa.core.properties.ResponseType;
import com.ps.saa.core.properties.SAAProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationSuccessHandler")
public class SAAAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SAAProperties saaProperties;
    @Autowired
    private ObjectMapper objectMapper;
    private RequestCache requestCache = new HttpSessionRequestCache();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("Sign in succeded.");
        if(ResponseType.JSON.equals(saaProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            String type = authentication.getClass().getSimpleName();
            response.getWriter().write(objectMapper.writeValueAsString(new RestResponse<String>(type)));
        } else{
            if(StringUtils.isNotBlank(saaProperties.getBrowser().getLoginSuccessUrl())){
                requestCache.removeRequest(request,response);
                setAlwaysUseDefaultTargetUrl(true);
                setDefaultTargetUrl(saaProperties.getBrowser().getLoginSuccessUrl());
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
