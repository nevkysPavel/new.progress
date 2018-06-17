package all;


import all.configuration.JavaConfiguration;
import all.configuration.WebAppInitializer;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.swing.*;

public class SystemTest {

    @BeforeClass
    public static void setup() {

        RestAssured.port = Integer.valueOf(9999);

//        String basePath = System.getProperty("server.base");
//        if(basePath==null){
//            basePath = "/rest-garage-sample/";
//        }
//        RestAssured.basePath = basePath;

        String baseHost = "http://127.0.0.1";

        RestAssured.baseURI = baseHost;

    }
    @Test
    public void systemTest()  {
        String string = get("/food/api/clients/get/client/1")
                .then().extract().body().asString();
        System.out.println(string);
//Given an app
        //Then add a user
        //Get user
        //Delete
        //Assure that it has been deleted


    }
}

