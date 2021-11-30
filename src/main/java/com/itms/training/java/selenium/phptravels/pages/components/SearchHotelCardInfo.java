package com.itms.training.java.selenium.phptravels.pages.components;

import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.selenium.phptravels.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHotelCardInfo extends BasePage {
    private String hotelName;
    private final WebElement container;

    public SearchHotelCardInfo(WebDriver webDriver, String hotelName) {
        super(webDriver);
        this.hotelName = hotelName;
        this.container = webDriver.findElement(By.id(hotelName.toLowerCase()));
    }

    public SearchHotelCardInfo(WebDriver webDriver, WebElement container) {
        super(webDriver);
        this.container = container;
    }

    public HotelCard getHotelCard() {
        HotelCard hotelCard = new HotelCard();
        hotelCard.setName(getHotelName());
        hotelCard.setLocation(getLocation());
        hotelCard.setStars(getStars());
        hotelCard.setRatings(getRatings());
        hotelCard.setCurrency(getCurrency());
        hotelCard.setPrice(getPrice());
        hotelCard.setNights(getNights());
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
        By byReview = By.className("review__text");
        By byStar = By.xpath(".//div[@class='stars la la-star']");
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byReview)))
                .findElements(byStar)
                .size();
    }

    private int getRatings() {
        By byRating = By.className("rating__text");
        return Integer.parseInt(
                webDriverWait
                        .until(ExpectedConditions.visibilityOf(container.findElement(byRating)))
                        .findElement(By.tagName("span"))
                        .getText()
        );
    }

    private String getCurrency() {
        By byPrice = By.className("price__num");
        return webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byPrice)))
                .findElement(By.tagName("small"))
                .getText();
    }

    private double getPrice() {
        By byPrice = By.className("price__num");
        return Double.parseDouble(
                webDriverWait
                        .until(ExpectedConditions.visibilityOf(container.findElement(byPrice)))
                        .findElement(By.tagName("strong"))
                        .getText()
        );
    }

    private int getNights() {
        By byCardPrice = By.className("card-price");
        String nights = webDriverWait
                .until(ExpectedConditions.visibilityOf(container.findElement(byCardPrice)))
                .findElement(By.className("mb-1"))
                .getText();

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(nights);
        if(m.find()) {
            return Integer.parseInt(m.group(0));
        }
        return 0;
    }








}
