package com.itms.training.java.selenium.phptravels.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Timespan;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SignUp {

    // TestNG Annotation
    @Parameters("browserName")
    @Test
    public void signUpTests(String browserName) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.getInstance(browserName).create();

        // PageLoad Timeout: Check performance of page
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // Should not use both implicitlyWait and WebDriverWait at the same time

        // ImplicitWait
        // webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // ExplicitWait
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

        webDriver.manage().window().maximize();
        webDriver.get("https://www.phptravels.net/login");

        // Best Practices for every element interaction
        WebElement btnCookieStop = webDriver.findElement(By.id("cookie_stop"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnCookieStop));
        btnCookieStop.click();

        WebElement lkSignUp = webDriver.findElement(By.linkText("Signup"));
        lkSignUp.click();

        // Input Signup Form
        WebElement tbFirstName = webDriver.findElement(By.name("first_name"));
        tbFirstName.clear();
        tbFirstName.sendKeys("ITMS");

        WebElement tbLastName = webDriver.findElement(By.name("last_name"));
        tbLastName.clear();
        tbLastName.sendKeys("Coaching");

        WebElement tbPhone = webDriver.findElement(By.name("phone"));
        tbPhone.clear();
        tbPhone.sendKeys("123456789");

        WebElement tbEmail = webDriver.findElement(By.name("email"));
        tbEmail.clear();
        tbEmail.sendKeys("itms.coaching@gmail.com");

        WebElement tbPassword = webDriver.findElement(By.name("password"));
        tbPassword.clear();
        tbPassword.sendKeys("123456");

        WebElement spanAccountType = webDriver.findElement(By.id("select2-account_type-container"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", spanAccountType);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(spanAccountType)).click();

        WebElement liAccountType = webDriver.findElement(By.xpath("//li[.='Supplier']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", liAccountType);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(liAccountType)).click();

        WebElement btnSignup = webDriver.findElement(By.xpath("//button[.='Signup']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", btnSignup);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnSignup)).click();

        Thread.sleep(5000);
    }

    @Parameters("browserName")
    @Test
    public void loginTestsSucess(String browserName) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.getInstance(browserName).create();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        webDriver.manage().window().maximize();

        webDriver.get("https://www.phptravels.net/login");

        WebElement tbEmail = webDriver.findElement(By.name("email"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tbEmail));
        tbEmail.clear();
        tbEmail.sendKeys("itms.coaching@gmail.com");

        WebElement tbPassword = webDriver.findElement(By.name("password"));
        tbPassword.clear();
        tbPassword.sendKeys("123456");

        WebElement btnLogin = webDriver.findElement(By.xpath("//button[normalize-space(.)='Login']"));
        executor.executeScript("arguments[0].scrollIntoView();", btnLogin);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();

        webDriverWait.until(ExpectedConditions.titleIs("Dashboard - PHPTRAVELS"));

        Thread.sleep(5000);
    }

    @Parameters("browserName")
    @Test
    public void loginTestsFail(String browserName) {
        WebDriver webDriver = WebDriverManager.getInstance(browserName).create();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        webDriver.manage().window().maximize();

        webDriver.get("https://www.phptravels.net/login");

        WebElement tbEmail = webDriver.findElement(By.name("email"));
        tbEmail.clear();
        tbEmail.sendKeys("xxx.coaching@gmail.com");

        WebElement tbPassword = webDriver.findElement(By.name("password"));
        tbPassword.clear();
        tbPassword.sendKeys("123456");

        WebElement btnLogin = webDriver.findElement(By.xpath("//button[normalize-space(.)='Login']"));
        executor.executeScript("arguments[0].scrollIntoView();", btnLogin);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();

        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@class='message']/div[@class='alert alert-danger failed']"));
        Assert.assertEquals(errorMessage.getText(), "Wrong credentials. try again!");

    }


    @Parameters("browserName")
    @Test
    public void TimeoutTests(String browserName) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.getInstance(browserName).create();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        webDriver.get("https://www.phptravels.net/login");

        WebElement tbEmail = webDriver.findElement(By.name("email"));
        tbEmail.clear();
        tbEmail.sendKeys("xxx.coaching@gmail.com");
    }
}
