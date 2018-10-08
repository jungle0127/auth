package com.ps.saa.browser.controller;

import com.ps.saa.core.dto.RestResponse;
import com.ps.saa.core.properties.SAAProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/authentication")
public class AuthorizeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SAAProperties saaProperties;

    @GetMapping("/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public RestResponse<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = this.requestCache.getRequest(request,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info(String.format("Target URL is %s",targetUrl));
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                this.redirectStrategy.sendRedirect(request,response,this.saaProperties.getBrowser().getLoginPage());
            }
        }
        return new RestResponse<String>("The service needs authentication, please redirect to login page.");
    }
}
