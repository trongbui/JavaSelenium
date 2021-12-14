package com.itms.training.java.selenium.phptravels.tests;

import com.itms.training.java.dto.Account;
import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.dto.HotelSearchFilter;
import com.itms.training.java.selenium.phptravels.pages.*;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderMenu;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderTopBar;
import com.itms.training.java.selenium.phptravels.tests.dataprovider.AccountDataProviders;
import com.itms.training.java.selenium.phptravels.tests.dataprovider.HotelCardDataProviders;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class EndToEndTests extends BaseTest {


    @Test(dataProvider = "FeatureHotelTestDataProvider", dataProviderClass = HotelCardDataProviders.class)
    public void hotelBooking(Account account, HotelSearchFilter filter, List<HotelCard> expectedHotelCards) throws ParseException {
        webDriver.get("https://www.phptravels.net/login");

        HeaderTopBar headerTopBar = new HeaderTopBar(webDriver);
        SignupPage signupPage = headerTopBar.openSignupPage();
        signupPage.signup(account);

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(account.getEmail(), account.getPassword());

        DashBoardPage dashBoardPage = new DashBoardPage(webDriver);
        webDriverWait.until(ExpectedConditions.titleIs(DashBoardPage.title));

        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        SearchHotelPage searchHotelPage = hotelPage.quickSearch(filter);
        List<HotelCard> actualHotelCards = hotelPage.getFeaturedHotelList();

        // Assert 2 HotelCard List by Object
        Assert.assertEquals(actualHotelCards.size(), expectedHotelCards.size());
        Assert.assertTrue(expectedHotelCards.containsAll(actualHotelCards));
    }
}
