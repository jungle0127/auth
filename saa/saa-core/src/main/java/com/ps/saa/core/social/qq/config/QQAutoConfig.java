package com.ps.saa.core.social.qq.config;


import com.ps.saa.core.properties.QQProperties;
import com.ps.saa.core.properties.SAAProperties;
import com.ps.saa.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "saa.social.qq", name = "appId")
public class QQAutoConfig extends SocialConfigurerAdapter {
    @Autowired
    private SAAProperties saaProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected  ConnectionFactory<?> createConnectionFactory(){
        String providerId = saaProperties.getSocial().getQq().getProviderId();
        String appId = saaProperties.getSocial().getQq().getAppId();
        String appSecret = saaProperties.getSocial().getQq().getAppSecret();
        return new QQConnectionFactory(providerId,appId,appSecret);
    }
}
