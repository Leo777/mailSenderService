package com.skelia.mailSender.mailSenderService;

import com.skelia.mailSender.mailSenderService.services.GraphSendService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestEndpoint {
    @Autowired
    private GraphSendService sendService;

    @PostMapping("/send")
    public ResponseEntity sendMail(@RequestBody JSONObject message, @RequestHeader HttpHeaders header) {
        String userEmail = header.get("from").get(0);
        return sendService.send(message, userEmail);
    }


}
