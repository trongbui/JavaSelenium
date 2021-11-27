package com.itms.training.java.selenium.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderTopBar extends BasePage{

    @FindBy(linkText = "Signup")
    private WebElement lkSignup;

    @FindBy(id = "cookie_stop")
    private WebElement btnGotIt;

    public HeaderTopBar(WebDriver webDriver) {
        super(webDriver);
    }

    public SignupPage openSignupPage() {
        click(lkSignup);
        return new SignupPage(webDriver);
    }

    public void cookieGotIt() {
        click(btnGotIt);
    }
}
