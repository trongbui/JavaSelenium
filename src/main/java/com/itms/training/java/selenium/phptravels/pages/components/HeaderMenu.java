package com.itms.training.java.selenium.phptravels.pages.components;

import com.itms.training.java.selenium.phptravels.pages.BasePage;
import com.itms.training.java.selenium.phptravels.pages.FlightPage;
import com.itms.training.java.selenium.phptravels.pages.HotelPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends BasePage {

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
        scrollClick(lkHotels);
        return new HotelPage(webDriver);
    }

    public FlightPage openFlights() {
        scrollClick(lkFlights);
        return new FlightPage(webDriver);
    }
}
