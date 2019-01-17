package com.skelia.mailSender.mailSenderService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class RestEndpoint {


        @Autowired
        private OAuth2RestTemplate template;

        @Value("${secured.service.url}")
        private String endpoint;

        @RequestMapping(value = "/message", method = RequestMethod.GET)
        public String getMessageFromSecuredService(){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject message = readingJsonFromFile();

            HttpEntity<String> entity = new HttpEntity<String>(message.toString(),headers);
            ResponseEntity<String> responseEntity = template.postForEntity(endpoint,entity,String.class);
            return responseEntity.getBody();
        }

    public JSONObject readingJsonFromFile() {

        JSONParser jsonParser = new JSONParser();

        File file = new File("C:\\dev\\skelia\\mailSenderService\\src\\main\\resources\\mail.json");

        Object object = null;
        try {
            object = jsonParser.parse(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) object;
       return jsonObject;
    }

}
