package edu.udacity.java.nano;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatroomTest {

    private static String USERNAME = "testUser";
    private static String BASE_URL = "http://localhost:8080/";
    private static String CHAT_URL = BASE_URL + "index?username=" + USERNAME;

    private static WebDriver webDriver;

    @Test
    public void contextLoads() {
    }

}