package com.ps.saa.core.properties.browser;

import com.ps.saa.core.properties.ResponseType;
import com.ps.saa.core.properties.SAAConstants;

public class BrowserProperties {
    private String loginPage = SAAConstants.DEFAULT_LOGIN_PAGE;
    private String loginProcessingUrl = SAAConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL;
    private String loginSuccessUrl;
    private ResponseType loginType = ResponseType.REDIRECT;
    private Integer rememberMeSeconds = 3600;
    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getLoginSuccessUrl() {
        return loginSuccessUrl;
    }

    public void setLoginSuccessUrl(String loginSuccessUrl) {
        this.loginSuccessUrl = loginSuccessUrl;
    }

    public ResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(ResponseType loginType) {
        this.loginType = loginType;
    }

    public Integer getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(Integer rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
