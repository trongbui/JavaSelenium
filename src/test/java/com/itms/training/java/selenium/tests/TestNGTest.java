package com.itms.training.java.selenium.tests;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGTest {

    @Test
    public void testAddInt() {
        int a = 10;
        int b = 20;
        System.out.println(a + b);
    }

    @Test
    public void testAddString() {
        String a = "Hello";
        String b = "World";
        System.out.println(a + b);
    }

    @Test
    public void signInTests() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php");
        Thread.sleep(5000);

        WebElement linkSignIn = driver.findElement(By.xpath("//a[@class='login']"));
//        WebElement linkSignIn = driver.findElement(By.className("login"));
//        WebElement linkSignIn = driver.findElement(By.partialLinkText("Sign in"));
//        WebElement linkSignIn = driver.findElement(By.cssSelector(".login"));

        linkSignIn.click();
        Thread.sleep(5000);

        WebElement tboxEmail = driver.findElement(By.id("email_create"));
        tboxEmail.sendKeys("itms.training@gmail.com");

        WebElement btnCreateAccount = driver.findElement(By.id("SubmitCreate"));
        btnCreateAccount.click();
        Thread.sleep(5000);

        WebElement h1 = driver.findElement(By.tagName("h1"));
        String actualH1 = h1.getText();

        String expectedH1 = "CREATE AN ACCOUNT";

        Assert.assertEquals(actualH1, expectedH1);

        driver.close();
    }

}
