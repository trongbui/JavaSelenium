package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.selenium.phptravels.pages.HeaderMenu;
import com.itms.training.java.selenium.phptravels.pages.HotelPage;
import com.itms.training.java.selenium.phptravels.pages.LoginPage;
import org.testng.annotations.Test;

public class HotelTests extends BaseTest {

    @Test
    public void searchHotelTest() {
        String email = "itms.coaching@gmail.com";
        String password = "123456";

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(email, password);

        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        hotelPage.specifyCityName("Singapore");
    }
}
