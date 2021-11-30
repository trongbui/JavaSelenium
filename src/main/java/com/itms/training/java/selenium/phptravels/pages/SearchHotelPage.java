package com.itms.training.java.selenium.phptravels.pages;

import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.selenium.phptravels.pages.components.SearchHotelCardInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchHotelPage extends BasePage{

    @FindBy(id = "data")
    private WebElement dataSection;

    public SearchHotelPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<HotelCard> getHotelCardList() {
        List<WebElement> hotelContainers = dataSection.findElements(By.xpath(".//li[contains(@class, 'hotels_amenities_')]"));
        List<HotelCard> hotelCards = new ArrayList<>();

        for (WebElement container : hotelContainers) {
            SearchHotelCardInfo searchHotelCardInfo = new SearchHotelCardInfo(webDriver, container);
            hotelCards.add(searchHotelCardInfo.getHotelCard());
        }

        return hotelCards;
    }
}
