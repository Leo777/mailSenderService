package com.skelia.mailSender.mailSenderService;

import com.nimbusds.oauth2.sdk.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

/**
 * Exposes a {@link RestTemplate} bean that adds an authentication header for the current OAuth2 client context.
 *
 * @author Geoff Bourne
 * @since May 2018
 */
@Configuration
@EnableOAuth2Client

public class OAuth2RestClientConfig {

    @Value("${spring.security.oauth2.client.registration.azure.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.azure.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.azure-oauth-provider.token-uri}")
    private String accessTokenUri;

    @Value("${spring.security.oauth2.client.provider.azure-oauth-provider.authorization-uri}")
    private String userAuthorizationUri;



    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login();
        }
    }
//    private RestTemplateBuilder restTemplateBuilder;
//
//    private OAuth2AuthorizedClientService authorizedClientService;
//
//    @Autowired
//    public OAuth2RestClientConfig(RestTemplateBuilder restTemplateBuilder, OAuth2AuthorizedClientService authorizedClientService) {
//        this.restTemplateBuilder = restTemplateBuilder;
//        this.authorizedClientService = authorizedClientService;
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return restTemplateBuilder.additionalInterceptors((httpRequest, bytes, clientHttpRequestExecution) -> {
//            OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//            OAuth2AuthorizedClient authedClient = authorizedClientService.loadAuthorizedClient(
//                    authentication.getAuthorizedClientRegistrationId(),
//                    authentication.getName());
//
//            String tokenType = authedClient.getAccessToken().getTokenType().getValue();
//            String token = authedClient.getAccessToken().getTokenValue();
//            final String authHeader = String.format("%s %s", tokenType, token);
//
////            log.debug("Intercepting HTTP request and adding OAuth2 authentication header");
//            httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, authHeader);
//
//
//            return clientHttpRequestExecution.execute(httpRequest, bytes);
//        })
//                .build();
//    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        return details;
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }
}
