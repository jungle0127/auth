package com.ps.saa.core.validate.code.filter;

import com.ps.saa.core.exception.ValidateCodeException;
import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.validate.code.sms.SMSCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SMSCodeFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        String requestMethod = httpServletRequest.getMethod();
        String loginProcessingUrl = "/saa" + SAAConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL;
        if(StringUtils.equals(requestURI,loginProcessingUrl) && StringUtils.equalsIgnoreCase(requestMethod,"POST")){
            try{
                validateSMSCode(new ServletWebRequest(httpServletRequest));
            } catch (ServletRequestBindingException e){
                logger.error(String.format("Validate sms code failed with exception:%s",e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private void validateSMSCode(ServletWebRequest request) throws ServletRequestBindingException {
        SMSCode smsCodeInSession = (SMSCode)this.sessionStrategy.getAttribute(request, SAAConstants.SMS_CODE_SESSION_KEY);
        String smsCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),SAAConstants.DEFAULT_SMSCODE_PARAMETER_NAME);
        if(StringUtils.isBlank(smsCodeInRequest)) {
            throw new ValidateCodeException("SMS code can not be null.");
        }
        if(smsCodeInSession == null) {
            throw new ValidateCodeException("SMS code does not exist.");
        }
        if(smsCodeInSession.isExpired()) {
            this.sessionStrategy.removeAttribute(request, SAAConstants.IMAGE_CODE_SESSION_KEY);
            throw new ValidateCodeException("SMS code is expired.");
        }
        if(!StringUtils.equals(smsCodeInRequest, smsCodeInSession.getCode())) {
            throw new ValidateCodeException("SMS code does not match");
        }
        this.sessionStrategy.removeAttribute(request,SAAConstants.SMS_CODE_SESSION_KEY);
    }
}
