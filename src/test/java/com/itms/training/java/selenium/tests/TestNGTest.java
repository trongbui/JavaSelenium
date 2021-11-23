package com.itms.training.java.selenium.tests;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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

    @Parameters("browser")
    @Test
    public void createAccount(String browser) throws InterruptedException {
        System.out.println(browser);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");

        WebElement btnSignIn = driver.findElement(By.className("login"));
        btnSignIn.click();

        WebElement emailTextbox = driver.findElement(By.id("email_create"));
        emailTextbox.sendKeys("dannt2@yopmail.com");

        WebElement btnSubmitEmail = driver.findElement(By.xpath("//*[@id = 'SubmitCreate']"));
        btnSubmitEmail.click();
        Thread.sleep(10000);

//        WebElement header = driver.findElement(By.tagName("h1"));
//        String actualHeading = header.getText();
//
//        String expectHeading = "CREATE AN ACCOUNT";
//
//        Assert.assertEquals(actualHeading, expectHeading);

        WebElement chkTitle = driver.findElement(By.id("id_gender2"));
        chkTitle.click();

        WebElement FirstnameTextbox = driver.findElement(By.id("customer_firstname"));
        FirstnameTextbox.sendKeys("Tam Dan");

        WebElement LastnameTextbox = driver.findElement(By.name("customer_lastname"));
        LastnameTextbox.sendKeys("Nguyen");

        WebElement Pwd = driver.findElement(By.id("passwd"));
        Pwd.sendKeys("Galaxy@2020");

        WebElement dobDays = driver.findElement(By.id("days"));
        Select slDays = new Select(dobDays);
        slDays.selectByIndex(5);

        WebElement dobMonths = driver.findElement(By.id("months"));
        Select slMonths = new Select(dobMonths);
        slMonths.selectByValue("5");

        Select slYears = new Select(driver.findElement(By.id("years")));
        slYears.selectByVisibleText("2010  ");

        WebElement Company = driver.findElement(By.id("company"));
        Company.sendKeys("Company Testing");

        WebElement CompanyAdd1 = driver.findElement(By.id("address1"));
        CompanyAdd1.sendKeys("Company Testing Address");

        WebElement CompanyAdd2 = driver.findElement(By.id("address2"));
        CompanyAdd2.sendKeys("7 floor");

        WebElement City = driver.findElement(By.id("city"));
        City.sendKeys("Ha Noi");

        Select slStates = new Select(driver.findElement(By.id("id_state")));
        slStates.selectByVisibleText("Florida");

        WebElement Postcode = driver.findElement(By.name("postcode"));
        Postcode.sendKeys("700000");

        WebElement Others = driver.findElement(By.id("other"));
        Others.sendKeys("\"Something to input here.\"");

        WebElement HomePhone = driver.findElement(By.id("phone"));
        HomePhone.sendKeys("0123456789");

        WebElement MobilePhone = driver.findElement(By.xpath("//*[@name = 'phone_mobile']"));
        MobilePhone.sendKeys("+84111222333");

        WebElement AddressAlias = driver.findElement(By.xpath("//*[@name = 'alias']"));
        AddressAlias.sendKeys(" For future reference");

        driver.close();
    }

    @Test
    public void testLocators() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.airbnb.com/");

        WebElement elPlacesToStays = driver.findElement(By.xpath("//button[.='Experiences']"));


    }
}
