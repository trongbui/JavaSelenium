package com.itms.training.java.selenium.phptravels.pages;

import com.itms.training.java.dto.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupPage extends BasePage{

    @FindBy(name = "first_name")
    private WebElement tbFirstName;

    @FindBy(name = "last_name")
    private WebElement tbLastName;

    @FindBy(name = "phone")
    private WebElement tbPhone;

    @FindBy(name = "email")
    private WebElement tbEmail;

    @FindBy(name = "password")
    private WebElement tbPassword;

    @FindBy(xpath = "//button[normalize-space(.)='Signup']")
    private WebElement btnSignup;

    public SignupPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputFirstName(String firstname) {
        inputText(tbFirstName, firstname);
    }

    public void inputLastName(String lastname) {
        inputText(tbLastName, lastname);
    }

    public void inputPhone(String phone) {
        inputText(tbPhone, phone);
    }

    public void inputEmail(String email) {
        inputText(tbEmail, email);
    }

    public void inputPassword(String password) {
        inputText(tbPassword, password);
    }

    public void clickSignup() {
        scrollClick(btnSignup);
    }

    public WebElement getTbFirstName() {
        return tbFirstName;
    }

    public WebElement getTbLastName() {
        return tbLastName;
    }

    public WebElement getTbPhone() {
        return tbPhone;
    }

    public WebElement getTbEmail() {
        return tbEmail;
    }

    public WebElement getTbPassword() {
        return tbPassword;
    }

    public WebElement getBtnSignup() {
        return btnSignup;
    }

    public void selectAccountType(String accountType) {
        By by = By.id("select2-account_type-container");
        scrollClick(webDriver.findElement(by));

        By byAccountType = By.xpath(String.format("//li[.='%s']", accountType));
        scrollClick(webDriver.findElement(byAccountType));
    }

    public void signup(String firstname, String lastname, String phone, String email, String password, String accountType) {
        inputFirstName(firstname);
        inputLastName(lastname);
        inputPhone(phone);
        inputEmail(email);
        inputPassword(password);
        selectAccountType(accountType);

        clickSignup();
    }

    public String getFirstNameRequiredMessage() {
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(tbFirstName))
                .getAttribute("validationMessage");
    }

    public String getLastNameRequiredMessage() {
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(tbLastName))
                .getAttribute("validationMessage");
    }

    public String getPhoneRequiredMessage() {
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(tbPhone))
                .getAttribute("validationMessage");
    }

    public String getRequiredMessage(WebElement el) {
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(el))
                .getAttribute("validationMessage");
    }

    public void signup(Account account) {
        signup(
                account.getFirstName(),
                account.getLastName(),
                account.getPhone(),
                account.getEmail(),
                account.getPassword(),
                account.getAccountType());
    }
}
