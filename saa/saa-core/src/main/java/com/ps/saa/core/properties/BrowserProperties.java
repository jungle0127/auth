package com.ps.saa.core.properties;

public class BrowserProperties {
    private String loginPage = SAAConstants.DEFAULT_LOGIN_PAGE;
    private String loginProcessingUrl = SAAConstants.DEFAULT_LOGIN_PROCESSING_URL;

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
}
