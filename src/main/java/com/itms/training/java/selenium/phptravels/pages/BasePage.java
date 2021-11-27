package com.itms.training.java.selenium.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    protected JavascriptExecutor jsExecutor;

    private int waitTimeout = 60;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(waitTimeout));
        jsExecutor = (JavascriptExecutor) webDriver;

        PageFactory.initElements(webDriver, this);
    }

    protected void click(WebElement el) {
        jsExecutor.executeScript("arguments[0].scrollIntoView();", el);
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(el))
                .click();
    }

    protected void inputText(WebElement el, String text) {
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(el));
        el.clear();
        el.sendKeys(text);
    }



}
