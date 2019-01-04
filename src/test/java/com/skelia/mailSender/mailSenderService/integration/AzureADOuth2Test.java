package com.skelia.mailSender.mailSenderService.integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AzureADOuth2Test {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Test
    public void shouldGetAuthenticationToken(){
//    restTemplate.execute();
//        restTemplate.
    }
}
