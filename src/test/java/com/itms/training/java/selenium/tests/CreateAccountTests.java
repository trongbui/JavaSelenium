package com.itms.training.java.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateAccountTests {

    @Parameters("browserName")
    @Test
    public void createAccountSuccessTests(String browserName) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.getInstance(browserName).create();

        webDriver.get("http://automationpractice.com/index.php");

        WebElement btnSignIn = webDriver.findElement(By.className("login"));
        btnSignIn.click();

        WebElement emailTextbox = webDriver.findElement(By.id("email_create"));
        emailTextbox.sendKeys("dannt2@yopmail.com");

        WebElement btnSubmitEmail = webDriver.findElement(By.xpath("//*[@id = 'SubmitCreate']"));
        btnSubmitEmail.click();
        Thread.sleep(10000);

//        WebElement header = driver.findElement(By.tagName("h1"));
//        String actualHeading = header.getText();
//
//        String expectHeading = "CREATE AN ACCOUNT";
//
//        Assert.assertEquals(actualHeading, expectHeading);

        WebElement chkTitle = webDriver.findElement(By.id("id_gender2"));
        chkTitle.click();

        WebElement FirstnameTextbox = webDriver.findElement(By.id("customer_firstname"));
        FirstnameTextbox.sendKeys("Tam Dan");

        WebElement LastnameTextbox = webDriver.findElement(By.name("customer_lastname"));
        LastnameTextbox.sendKeys("Nguyen");

        WebElement Pwd = webDriver.findElement(By.id("passwd"));
        Pwd.sendKeys("Galaxy@2020");

        WebElement dobDays = webDriver.findElement(By.id("days"));
        Select slDays = new Select(dobDays);
        slDays.selectByIndex(5);

        WebElement dobMonths = webDriver.findElement(By.id("months"));
        Select slMonths = new Select(dobMonths);
        slMonths.selectByValue("5");

        Select slYears = new Select(webDriver.findElement(By.id("years")));
        slYears.selectByVisibleText("2010  ");

        WebElement Company = webDriver.findElement(By.id("company"));
        Company.sendKeys("Company Testing");

        WebElement CompanyAdd1 = webDriver.findElement(By.id("address1"));
        CompanyAdd1.sendKeys("Company Testing Address");

        WebElement CompanyAdd2 = webDriver.findElement(By.id("address2"));
        CompanyAdd2.sendKeys("7 floor");

        WebElement City = webDriver.findElement(By.id("city"));
        City.sendKeys("Ha Noi");

        Select slStates = new Select(webDriver.findElement(By.id("id_state")));
        slStates.selectByVisibleText("Florida");

        WebElement Postcode = webDriver.findElement(By.name("postcode"));
        Postcode.sendKeys("700000");

        WebElement Others = webDriver.findElement(By.id("other"));
        Others.sendKeys("\"Something to input here.\"");

        WebElement HomePhone = webDriver.findElement(By.id("phone"));
        HomePhone.sendKeys("0123456789");

        WebElement MobilePhone = webDriver.findElement(By.xpath("//*[@name = 'phone_mobile']"));
        MobilePhone.sendKeys("+84111222333");

        WebElement AddressAlias = webDriver.findElement(By.xpath("//*[@name = 'alias']"));
        AddressAlias.sendKeys(" For future reference");

        webDriver.close();
    }
}
