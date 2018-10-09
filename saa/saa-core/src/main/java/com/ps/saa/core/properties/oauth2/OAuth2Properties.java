package com.ps.saa.core.properties.oauth2;

public class OAuth2Properties {
    private OAuth2ClientProperties[] client = {};
    private TokenProperties token = new TokenProperties();

    public TokenProperties getToken() {
        return token;
    }

    public void setToken(TokenProperties token) {
        this.token = token;
    }

    public OAuth2ClientProperties[] getClient() {
        return client;
    }

    public void setClient(OAuth2ClientProperties[] client) {
        this.client = client;
    }
}
