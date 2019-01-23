package com.skelia.mailSender.mailSenderService.integration;

import com.skelia.mailSender.mailSenderService.services.GraphSendService;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphSendServiceIntegrationTest {

    @Autowired
    private GraphSendService sendService;

    @Test
    public void testGraphMailSender(){
       JSONObject message = readMessageFromFile();
      ResponseEntity responseEntity = sendService.send(message,"leu.barycheuski@skelia.com");
       assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
    }

    private JSONObject readMessageFromFile() {

        JSONParser jsonParser = new JSONParser();

        File file = new File("./src/main/resources/mail.json");

        Object object = null;
        byte[] fileContent = null;
        try {
            object = jsonParser.parse(new FileReader(file));
            fileContent = FileUtils.readFileToByteArray(new File("./src/main/resources/hello world.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        addAttachment((JSONObject) object, fileContent);

        return (JSONObject) object;
    }

    private void addAttachment(JSONObject object, byte[] fileContent) {
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        JSONObject jsonObject = object;
        JSONObject message = (JSONObject) jsonObject.get("message");
        JSONObject attachment = new JSONObject();
        attachment.put("@odata.type", "#microsoft.graph.fileAttachment");
        attachment.put("name", "hello world.txt");
        attachment.put("contentBytes", encodedString);
        message.put("attachments", Arrays.asList(attachment));
    }
}
