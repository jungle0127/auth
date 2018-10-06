package com.ps.saa.core.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.saa.core.social.qq.api.QQ;
import com.ps.saa.core.social.qq.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * http://wiki.connect.qq.com/get_user_info
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private static final String URL_GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private String appId;
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();
    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPEN_ID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result,"\"openid\":","}");

    }

    @Override
    public QQUserInfo getUserInfo() throws IOException {
        String url = String.format(URL_GET_USER_INFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);

        return objectMapper.readValue(result,QQUserInfo.class);
    }
}
