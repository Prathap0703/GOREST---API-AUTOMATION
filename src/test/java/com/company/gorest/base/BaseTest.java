package com.company.gorest.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);

        String baseUrl = prop.getProperty("base.url");
        String token = prop.getProperty("auth.token");

        RestAssured.baseURI = baseUrl;

        request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
    }
}
