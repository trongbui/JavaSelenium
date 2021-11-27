package com.itms.training.java.selenium.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends BasePage{

    @FindBy(linkText = "Home")
    private WebElement lkHome;

    @FindBy(linkText = "Hotels")
    private WebElement lkHotels;

    @FindBy(linkText = "Flights")
    private WebElement lkFlights;

    @FindBy(linkText = "Tours")
    private WebElement lkTours;

    @FindBy(linkText = "Cars")
    private WebElement lkCars;

    @FindBy(linkText = "Visa")
    private WebElement lkVisa;

    @FindBy(linkText = "Blog")
    private WebElement lkBlog;

    public HeaderMenu(WebDriver webDriver) {
        super(webDriver);
    }

    public HotelPage openHotels(){
        click(lkHotels);
        return new HotelPage(webDriver);
    }

    public FlightPage openFlights() {
        click(lkFlights);
        return new FlightPage(webDriver);
    }
}
