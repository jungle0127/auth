package com.ps.saa.core.social.qq.connect;

import com.ps.saa.core.social.qq.api.QQ;
import com.ps.saa.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

public class QQApiAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;// We consider QQ service always works well.
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        try {
            QQUserInfo userInfo = api.getUserInfo();
            values.setDisplayName(userInfo.getNickname());
            values.setImageUrl(userInfo.getFigureurl_qq_1());
            values.setProfileUrl(null);
            values.setProviderUserId(userInfo.getOpenId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {// for binding/unbinding
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //DO NOTHING
    }
}
