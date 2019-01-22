package com.skelia.mailSender.mailSenderService.utils;

import java.util.ResourceBundle;

public class UserUtils {

    public static String getUserId(String email) {
        ResourceBundle rb = ResourceBundle.getBundle("user");
        return rb.getString(email);
    }
}
