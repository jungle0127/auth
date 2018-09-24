package com.ps.saa.core.properties;

public class BrowserProperties {
    private String loginPage = SAAConstants.DEFAULT_LOGIN_PAGE;
    private String loginProcessingUrl = SAAConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL;
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

    public Integer getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(Integer rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
