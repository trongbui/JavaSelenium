package com.itms.training.java.selenium.phptravels.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.FilterResult;
import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.dto.HotelSearchFilter;
import com.itms.training.java.selenium.phptravels.pages.SearchHotelPage;
import com.itms.training.java.selenium.phptravels.pages.components.HeaderMenu;
import com.itms.training.java.selenium.phptravels.pages.HotelPage;
import com.itms.training.java.utils.TestAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HotelTests extends BaseTest {

    /**
     * SearchHotelTest with hard-code test data
     * @throws ParseException
     * @throws JsonProcessingException
     */
    @Test
    public void searchHotelTest() throws ParseException, JsonProcessingException {
        String cityName = "Singapore";
        String checkinDate = "12/12/2021";
        String checkoutDate = "13/12/2021";
        int rooms = 2;
        int adults = 3;
        int[] childs = {6, 9};
        String nationality = "Viet Nam";

        String expectedResult = "[" +
                "{\"name\":\"Rendezvous Hotels\",\"location\":\"Singapore\",\"stars\":2,\"ratings\":2,\"currency\":\"USD\",\"price\":93.5,\"nights\":1},\n" +
                "{\"name\":\"Swissotel Le Plaza Basel\",\"location\":\"Singapore\",\"stars\":4,\"ratings\":4,\"currency\":\"USD\",\"price\":88.0,\"nights\":1}\n" +
                "]";

        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        SearchHotelPage searchHotelPage = hotelPage.quickSearch(cityName, checkinDate, checkoutDate, rooms, adults, childs, nationality);

        List<HotelCard> actualHotelCards = searchHotelPage.getHotelCardList();

        ObjectMapper objectMapper = new ObjectMapper();
        List<HotelCard> expectedHotelCards = objectMapper.readValue(expectedResult, new TypeReference<List<HotelCard>>() {});

        Assert.assertEquals(actualHotelCards.size(), expectedHotelCards.size());
        Assert.assertTrue(actualHotelCards.containsAll(expectedHotelCards));
        Assert.assertTrue(expectedHotelCards.containsAll(actualHotelCards));
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
        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();
        ObjectMapper objectMapper = new ObjectMapper();

        HotelSearchFilter hotelSearch = objectMapper.readValue(filters.toString(), HotelSearchFilter.class);
        List<HotelCard> expectedHotelCard = objectMapper.readValue(results.toString(), new TypeReference<List<HotelCard>>() {});

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

//        System.out.println(results);
        List<HotelCard> actualResults = hotelPage.getFeaturedHotelList();
        System.out.println(objectMapper.writeValueAsString(actualResults));
    }

    @Test (dataProvider = "Search_Object_Hotels")
    public void featureHotelTestDataProviderObject(HotelSearchFilter filter, List<HotelCard> expectedHotelCards) throws ParseException, JsonProcessingException {
        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        SearchHotelPage searchHotelPage = hotelPage.quickSearch(filter);
        List<HotelCard> actualHotelCards = hotelPage.getFeaturedHotelList();

        System.out.println(actualHotelCards);
        System.out.println(expectedHotelCards);

        // Assert 2 HotelCard List by Object
        Assert.assertEquals(actualHotelCards.size(), expectedHotelCards.size());
        Assert.assertTrue(expectedHotelCards.containsAll(actualHotelCards));

//        // Convert to JSON List to practice comparing 2 JSON Lists
//        ObjectMapper mapper = new ObjectMapper();
//        List<JSONObject> actualHotelJsonList = new ArrayList<>();
//        List<JSONObject> expectedHotelJsonList = new ArrayList<>();
//        for (int i = 0; i < actualHotelCards.size(); i++) {
//            actualHotelJsonList.add(new JSONObject(mapper.writeValueAsString(actualHotelCards.get(i))));
//            expectedHotelJsonList.add(new JSONObject(mapper.writeValueAsString(expectedHotelCards.get(i))));
//        }
//
//        Assert.assertTrue(TestAssert.isHotelJsonListEqual(actualHotelJsonList, expectedHotelJsonList));
    }

    @Test (dataProvider = "FilterResult")
    public void featureHotelTestDataProviderFilterResult(FilterResult filterResult) throws ParseException, JsonProcessingException {
        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        HotelPage hotelPage = headerMenu.openHotels();

        SearchHotelPage searchHotelPage = hotelPage.quickSearch(filterResult.getFilters());
        List<HotelCard> actualHotelCards = hotelPage.getFeaturedHotelList();
        List<HotelCard> expectedHotelCards = filterResult.getResults();

        // Assert 2 HotelCard List by Object
        Assert.assertEquals(actualHotelCards.size(), expectedHotelCards.size());
        Assert.assertTrue(expectedHotelCards.containsAll(actualHotelCards));
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

    @DataProvider(name = "Search_Object_Hotels")
    public Object[][] searchObjectHotels() throws IOException {
        File fileSearchDataDriven = new File("src/test/resources/data/search_hotels.json");
        ObjectMapper mapper = new ObjectMapper();

        JSONArray testData = new JSONArray(Files.readFile(fileSearchDataDriven));
        Object [][] array = new Object[testData.length()][];

        for (int i = 0; i < testData.length(); i++) {

            JSONObject data = (JSONObject) testData.get(i);
            JSONObject filters = data.getJSONObject("filters");
            JSONArray expectedResults = data.getJSONArray("results");

            HotelSearchFilter filter = mapper.readValue(data.getJSONObject("filters").toString(), HotelSearchFilter.class);
            List<HotelCard> hotelCards = mapper.readValue(data.getJSONArray("results").toString(), new TypeReference<List<HotelCard>>() {});

            Object [] tc = new Object[]{filter, hotelCards};
            array[i] = tc;
        }

        return array;
    }

    @DataProvider(name = "FilterResult")
    public Object[][] filterResult() throws IOException {
        File fileSearchDataDriven = new File("src/test/resources/data/search_hotels.json");
        ObjectMapper mapper = new ObjectMapper();

        List<FilterResult> filterResults = mapper.readValue(fileSearchDataDriven, new TypeReference<List<FilterResult>>() {});
        Object[][] array = new Object[filterResults.size()][];
        for (int i = 0; i < filterResults.size(); i++) {
            Object[] fr = new Object[] {filterResults.get(i)};
            array[i] = fr;
        }
        return array;
    }
}
