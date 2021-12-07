package com.itms.training.java.selenium.phptravels.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.WeakHashMap;
import java.util.regex.Pattern;

public class Verification extends BaseTest{


    @Test
    public void verifyTitle() {
        String title = "Login - PHPTRAVELS";
        String otherTitle = "Signup - PHPTRAVELS";

        // Choose 1 of following assert method to verify the information
        webDriverWait.until(ExpectedConditions.titleIs(title));
        // or
        webDriverWait.until(ExpectedConditions.titleContains(title));
        // or
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.titleIs(otherTitle)));
        // or
        Assert.assertEquals(title, webDriver.getTitle());
        // or
        Assert.assertNotEquals(otherTitle, webDriver.getTitle());
    }

    @Test
    public void verifyVisualText() {
        String visualText = "Please enter your account credentials below";
        By by = By.xpath("//div[@class='modal-header']//p");

        // Choose 1 of following assert method to verify the information
        webDriverWait.until(ExpectedConditions.textToBe(by, visualText));
        // or
        webDriverWait.until(ExpectedConditions.textMatches(by, Pattern.compile("^.*account credentials.*$")));
        // or
        Assert.assertEquals(visualText,
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText());
    }

    @Test
    public void verifyPlaceHolder() {
        String placeholderText = "Email";
        String attribute = "placeholder";
        By by = By.name("email");

        // Choose 1 of following assert method to verify the information
        webDriverWait.until(ExpectedConditions.attributeToBe(by, "placeholder", placeholderText));
        // or
        webDriverWait.until(ExpectedConditions.attributeContains(by, "placeholder", placeholderText));
        // or
        Assert.assertEquals(placeholderText, webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).getAttribute("placeholder"));
    }

    @Test
    public void verifyValueText() {
        String text = "itms";
        String attribute = "value";
        By by = By.name("email");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(by))
                .sendKeys(text);

        // choose 1 from assert list to verify the infomation
        //===========================
        webDriverWait.until(ExpectedConditions.attributeToBe(by, "value", text));
        // or
        webDriverWait.until(ExpectedConditions.attributeContains(by, "value", text));
        // or
        Assert.assertEquals(text,
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).getAttribute("value"));
        //===========================
    }

    @Test
    public void verifyValidationMessage() {
        String text = "Please fill out this field.";
        String attribute = "validationMessage";
        By by = By.name("email");
        By bySubmit = By.xpath("//button[normalize-space(.)='Login']");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(bySubmit))
                .click();

        // choose 1 from following verifications
        webDriverWait.until(ExpectedConditions.attributeToBe(by, "validationMessage", text));
        // or
        webDriverWait.until(ExpectedConditions.attributeContains(by, "validationMessage", text));
        // or
        Assert.assertEquals(text,
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).getAttribute("validationMessage"));
    }

    @Test
    public void verifyPassword() {
        By by = By.name("password");
        String text = "123456";

        // Choose 1 of following assert method to verify the information
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by))
                .sendKeys(text);
        // or
        webDriverWait.until(ExpectedConditions.attributeToBe(by, "value", text));
    }

    @Test
    public void verifyButtonText() {
        By by = By.xpath("//button[normalize-space(.)='Login']");

        Assert.assertEquals("Login",
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText());
    }

    @Test
    public void verifyCheckbox() {
        By by = By.id("rememberchb");

        Assert.assertFalse(webDriver.findElement(by).isSelected());

        webDriver.findElement(by).click();

        Assert.assertTrue(webDriver.findElement(by).isSelected());
    }
}
