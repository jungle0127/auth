package com.ps.saa.core.social.qq.connect;

import com.ps.saa.core.social.qq.api.QQ;
import com.ps.saa.core.social.qq.api.impl.QQImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.stereotype.Component;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;
    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
     */
    public QQServiceProvider(String appId,String appSecret) {
        super(new OAuth2Template(appId,appSecret,AUTHORIZE_URL,ACCESS_TOKEN_URL));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
