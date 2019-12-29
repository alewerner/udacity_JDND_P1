package edu.udacity.java.nano;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatroomTest {

    private static String USERNAME = "tester";
    private static String USERNAME_2 = "tester2";
    private static String BASE_URL = "http://localhost:8080/";
    private static String CHAT_URL = BASE_URL + "index?username=" + USERNAME;
    private static String CHAT_URL_2 = BASE_URL + "index?username=" + USERNAME_2;

    private static WebDriver webDriver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        webDriver.get(BASE_URL);
        Assert.assertEquals(webDriver.getTitle(), "Chat Room Login");
    }

    @Test
    public void testJoin() {
        webDriver.get(BASE_URL);

        WebElement usernameInput = webDriver.findElement(By.id("username"));
        usernameInput.sendKeys(USERNAME);

        WebElement loginButton = webDriver.findElement(By.className("submit"));
        loginButton.click();

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(currentUrl, CHAT_URL);
    }

    @Test
    public void testChat() {
        String message = "Testing";

        webDriver.get(CHAT_URL);

        WebElement messageInput = webDriver.findElement(By.id("msg"));
        messageInput.sendKeys(message);

        WebElement sendButton = webDriver.findElement(By.id("send"));
        sendButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("message-content"), 1));

        List<WebElement> messageElements = webDriver.findElements((By.className("message-content")));
        WebElement messageElement = messageElements.get(messageElements.size() - 1);
        Assert.assertEquals(USERNAME + "：" + message, messageElement.getText());
    }


    @Test
    public void testChatWithTwoUsers() {
        String messageUser1 = "Hello User 2";
        String messageUser2 = "Hello User 1";

        webDriver.get(CHAT_URL);

        WebElement messageInput1 = webDriver.findElement(By.id("msg"));
        messageInput1.sendKeys(messageUser1);

        WebElement sendButton = webDriver.findElement(By.id("send"));
        sendButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("message-content"), 1));

        List<WebElement> messageElements = webDriver.findElements((By.className("message-content")));
        WebElement messageElement = messageElements.get(messageElements.size() - 1);
        Assert.assertEquals(USERNAME + "：" + messageUser1, messageElement.getText());

        webDriver.get(CHAT_URL_2);

        WebElement messageInput2 = webDriver.findElement(By.id("msg"));
        messageInput2.sendKeys(messageUser2);

        WebElement sendButton_2 = webDriver.findElement(By.id("send"));
        sendButton_2.click();

        WebDriverWait wait_2 = new WebDriverWait(webDriver, 10);
        wait_2.until(ExpectedConditions.numberOfElementsToBe(By.className("message-content"), 1));

        List<WebElement> messageElements_2 = webDriver.findElements((By.className("message-content")));
        WebElement messageElement_2 = messageElements_2.get(messageElements.size() - 1);
        Assert.assertEquals(USERNAME_2 + "：" + messageUser2, messageElement_2.getText());
    }

    @Test
    public void testLeave() {
        webDriver.get(CHAT_URL);
        webDriver.findElement(By.id("exit")).click();
        Assert.assertEquals(BASE_URL, webDriver.getCurrentUrl());
    }

    @AfterClass
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}