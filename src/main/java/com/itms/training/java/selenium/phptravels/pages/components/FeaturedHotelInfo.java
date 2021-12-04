package com.itms.training.java.selenium.phptravels.pages.components;

import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.selenium.phptravels.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeaturedHotelInfo extends BasePage {

    private String hotelName;
    private final WebElement container;

    public FeaturedHotelInfo(WebDriver webDriver, String hotelName) {
        super(webDriver);
        By byContainer = By.xpath(String.format("//div[@class='owl-item active']/div[contains(@class, 'card-item')][.//h3/a[normalize-space(.)='%s']]", hotelName));
        this.hotelName = hotelName;
        this.container = webDriver.findElement(byContainer);
    }

    public FeaturedHotelInfo(WebDriver webDriver, WebElement container) {
        super(webDriver);
        this.container = container;
    }

    public HotelCard getHotelCard() {
        HotelCard hotelCard = new HotelCard();
        hotelCard.setName(getHotelName());
        hotelCard.setLocation(getLocation());
        hotelCard.setStars(getStars());
        hotelCard.setCurrency(getCurrency());
        hotelCard.setPrice(getPrice());
        return hotelCard;
    }

    private String getHotelName() {
        By byHotelName = By.className("card-title");
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byHotelName)))
                .getText()
                .trim();
    }

    private String getLocation() {
        By byLocation = By.className("card-meta");
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byLocation)))
                .getText()
                .trim();
    }

    private int getStars() {
        By byReview = By.className("card-rating");
        By byStar = By.xpath(".//div[@class='la la-star-o']");
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byReview)))
                .findElements(byStar)
                .size();
    }

    private String getCurrency() {
        By byPrice = By.className("price__num");
        String price = webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byPrice)))
                .getText();

        Pattern p = Pattern.compile("\\S+");
        Matcher m = p.matcher(price);
        if(m.find()) {
            return m.group(0);
        }
        return null;
    }

    private double getPrice() {
        By byPrice = By.className("price__num");
        String price = webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byPrice)))
                .getText();

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(price);
        if(m.find()) {
            return Double.parseDouble(m.group(0));
        }
        return 0;
    }

}
