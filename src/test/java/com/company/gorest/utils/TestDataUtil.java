package com.company.gorest.utils;

import java.util.UUID;

public class TestDataUtil {

    public static String getRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0,5) + "@mail.com";
    }

    public static String getRandomName() {
        return "TestUser_" + UUID.randomUUID().toString().substring(0,5);
    }
}
