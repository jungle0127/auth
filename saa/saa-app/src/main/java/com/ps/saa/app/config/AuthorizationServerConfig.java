package com.ps.saa.app.config;

import com.ps.saa.core.properties.SAAProperties;
import com.ps.saa.core.properties.oauth2.OAuth2ClientProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {
    private AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SAAProperties saaProperties;
//    @Autowired
    private TokenStore tokenStore = new InMemoryTokenStore();
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder clientDetailsServiceBuilder = clients.inMemory();
        OAuth2ClientProperties[] clientConfigArray = saaProperties.getOauth2().getClient();
        if(ArrayUtils.isNotEmpty(clientConfigArray)){
            for(OAuth2ClientProperties clientConfig: clientConfigArray){
                clientDetailsServiceBuilder.withClient(clientConfig.getClientId())
                        .secret(clientConfig.getClientSecret())
                        .accessTokenValiditySeconds(clientConfig.getAccessTokenValiditySeconds())
                        .authorizedGrantTypes("refresh_token","password")
                        .scopes("all","read","write");
            }
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints//.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
