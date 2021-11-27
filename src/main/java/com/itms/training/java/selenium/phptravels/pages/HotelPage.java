package com.itms.training.java.selenium.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelPage extends BasePage{

    @FindBy(id = "select2-hotels_city-container")
    private WebElement sl2HotelCityContainer;

    @FindBy(xpath = "//input[@aria-controls='select2-hotels_city-results']")
    private WebElement tbHotelCityResult;

    @FindBy(xpath = "//input[@id='checkin']")
    private WebElement tbCheckIn;

    public HotelPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void specifyCityName(String cityName) {
        click(sl2HotelCityContainer);
        inputText(tbHotelCityResult, cityName);

        String xpathLinkCity = String.format("//li[contains(normalize-space(.), '%s')]", cityName);
        WebElement optCityName = webDriver.findElement(By.xpath(xpathLinkCity));

        click(optCityName);
    }

    // 1/12/2021
    public void specifyCheckInDate(String date) throws ParseException {
        Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        click(tbCheckIn);
        String xpathDay = String.format("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'display: block;')]//td[contains(@class, 'day  new') and .='%s']", checkInDate.getDay());
        WebElement day = webDriver.findElement(By.xpath(xpathDay));
        click(day);
    }


}
