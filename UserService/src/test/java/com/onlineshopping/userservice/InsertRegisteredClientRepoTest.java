package com.onlineshopping.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@SpringBootTest
public class InsertRegisteredClientRepoTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RegisteredClientRepository clientRepository;


    /*

    * This Test Case is used to create Client in the Database


    @Test
    @Commit
    public void insertClientToDB(){
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("productService")
                .clientSecret(passwordEncoder.encode("product"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("http://127.0.0.1:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        clientRepository.save(oidcClient);

    }

     */
}
