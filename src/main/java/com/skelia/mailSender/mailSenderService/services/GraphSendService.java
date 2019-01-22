package com.skelia.mailSender.mailSenderService.services;

import com.skelia.mailSender.mailSenderService.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class GraphSendService implements MailSenderService {

    private static final String URI = "https://graph.microsoft.com/v1.0/users/";

    private static final String ACTION = "/sendMail";

    @Autowired
    private OAuth2RestTemplate template;

    public ResponseEntity send(JSONObject message, String email) {

        String userId = UserUtils.getUserId(email);

        if (StringUtils.isBlank(userId)) {
            return new ResponseEntity<>("Empty or invalid user email: " + email, HttpStatus.BAD_REQUEST);
        }

        String endpoint = URI + userId + ACTION;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message.toString(), headers);
        ResponseEntity responseEntity = template.postForEntity(endpoint, entity, String.class);

        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }
}
