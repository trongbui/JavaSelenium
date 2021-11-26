package com.itms.training.java.selenium.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderTopBar extends BasePage{

    @FindBy(linkText = "Signup")
    private WebElement lkSignup;

    public HeaderTopBar(WebDriver webDriver) {
        super(webDriver);
    }

    public void signUp() {
        click(lkSignup);
    }
}
