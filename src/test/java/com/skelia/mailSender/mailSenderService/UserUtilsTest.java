package com.skelia.mailSender.mailSenderService;

import com.skelia.mailSender.mailSenderService.utils.UserUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserUtilsTest {


    @Test
    public void testGetngUserIdFromCongif(){
        assertEquals(UserUtils.getUserId("test.user@gmail.com"),"test_id");
    }
}
