package com.itms.training.java.selenium.phptravels.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.selenium.phptravels.pages.SearchHotelPage;
import com.itms.training.java.selenium.phptravels.pages.components.FeaturedHotelInfo;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderMenu;
import com.itms.training.java.selenium.phptravels.pages.HotelPage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void featureHotelTest() throws ParseException, IOException {
        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        File fileSearchDataDriven = new File("src/test/resources/data/search_hotels.json");
        ObjectMapper objectMapper = new ObjectMapper();

        JSONArray testData = new JSONArray(Files.readFile(fileSearchDataDriven));

        for (int i = 0; i < testData.length(); i++) {
            System.out.println("============== Star Test ===========");
            JSONObject data = (JSONObject) testData.get(i);
            JSONObject filters = data.getJSONObject("filters");
            JSONArray expectedResults = data.getJSONArray("results");

            String cityName = filters.getString("cityName");
            String checkinDate = filters.getString("checkinDate");
            String checkoutDate = filters.getString("checkoutDate");
            int rooms = filters.getInt("rooms");
            int adults = filters.getInt("adults");

            JSONArray dataChilds = filters.getJSONArray("childs");
            int [] childs = new int[dataChilds.length()];
            for (int j = 0; j < dataChilds.length(); j++ ) {
                childs[j] = dataChilds.getInt(j);
            }
            String nationality = filters.getString("nationality");
            SearchHotelPage searchHotelPage = hotelPage.quickSearch(cityName, checkinDate, checkoutDate, rooms, adults, childs, nationality);

            System.out.println(expectedResults);

            List<HotelCard> actualResults = hotelPage.getFeaturedHotelList();
            for (HotelCard hotelCard: actualResults) {
                System.out.println(hotelCard.toString());
            }

            System.out.println("============== End Test ===========");

        }
    }

    @Test (dataProvider = "Search_Hotels")
    public void featureHotelTestDataProvider(JSONObject filters, JSONArray results) throws ParseException, IOException {
        System.out.println(filters);
        System.out.println(results);

        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        String cityName = filters.getString("cityName");
        String checkinDate = filters.getString("checkinDate");
        String checkoutDate = filters.getString("checkoutDate");
        int rooms = filters.getInt("rooms");
        int adults = filters.getInt("adults");

        JSONArray dataChilds = filters.getJSONArray("childs");
        int [] childs = new int[dataChilds.length()];
        for (int j = 0; j < dataChilds.length(); j++ ) {
            childs[j] = dataChilds.getInt(j);
        }
        String nationality = filters.getString("nationality");
        SearchHotelPage searchHotelPage = hotelPage.quickSearch(cityName, checkinDate, checkoutDate, rooms, adults, childs, nationality);

        System.out.println(results);
        List<HotelCard> actualResults = hotelPage.getFeaturedHotelList();

    }

    @DataProvider(name = "Search_Hotels")
    public Object[][] searchHotels() throws IOException {

        File fileSearchDataDriven = new File("src/test/resources/data/search_hotels.json");
        JSONArray testData = new JSONArray(Files.readFile(fileSearchDataDriven));
        Object [][] array = new Object[testData.length()][];

        for (int i = 0; i < testData.length(); i++) {

            JSONObject data = (JSONObject) testData.get(i);
            JSONObject filters = data.getJSONObject("filters");
            JSONArray expectedResults = data.getJSONArray("results");

            Object [] tc = new Object[]{filters, expectedResults};
            array[i] = tc;
        }

        return array;
    }
}
