package com.skelia.mailSender.mailSenderService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.*;

public class ReadJsonFromFile {


    @Test
    public void readingJsonFromFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        File file = new File("C:\\dev\\skelia\\mailSenderService\\src\\main\\resources\\mail.json");

        Object object = jsonParser.parse(new FileReader(file));

        JSONObject jsonObject = (JSONObject) object;
        System.out.println(jsonObject);
    }
}
