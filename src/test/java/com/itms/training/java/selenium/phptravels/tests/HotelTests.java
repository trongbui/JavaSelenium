package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.selenium.phptravels.pages.SearchHotelPage;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderMenu;
import com.itms.training.java.selenium.phptravels.pages.HotelPage;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class HotelTests extends BaseTest {

    @Test
    public void searchHotelTest() throws ParseException {
        String cityName = "Singapore";
        String checkinDate = "04/12/2021";
        String checkoutDate = "06/12/2021";
        int rooms = 3;
        int adults = 3;
        int[] childs = {6, 9};
        String nationality = "Viet Nam";

        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        SearchHotelPage searchHotelPage = hotelPage.quickSearch(cityName, checkinDate, checkoutDate, rooms, adults, childs, nationality);

        List<HotelCard> hotelCards = searchHotelPage.getHotelCardList();
        for (HotelCard hotelCard: hotelCards) {
            System.out.println(hotelCard.toString());
        }
    }
}
