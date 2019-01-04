package com.skelia.mailSender.mailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

    private String json = "{\n" +
            "  \"Letters\": [\n" +
            "    {\n" +
            "      \"Message\": {\n" +
            "        \"Subject\": \"Graph Test pdf\",\n" +
            "        \"Attachments\": [\n" +
            "\n" +
            "        ],\n" +
            "        \"Body\": {\n" +
            "          \"ContentType\": \"Text\",\n" +
            "          \"Content\": \"Has been Sent pdf file.\"\n" +
            "        },\n" +
            "        \"ToRecipients\": [\n" +
            "          {\n" +
            "            \"EmailAddress\": {\n" +
            "              \"Address\": \"borichevskiy@gmail.com\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";


        @Autowired
        private OAuth2RestTemplate template;

        @Value("${secured.service.url}")
        private String endpoint;

        @RequestMapping(value = "/message", method = RequestMethod.GET)
        public String getMessageFromSecuredService(){
            ResponseEntity<String> entity = template.postForEntity(endpoint,json,String.class);
            return entity.getBody();
        }


}
