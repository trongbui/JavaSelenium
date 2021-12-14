package com.itms.training.java.selenium.phptravels.tests.dataprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.Account;
import com.itms.training.java.dto.FilterResult;
import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.dto.HotelSearchFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelCardDataProviders {

    /**
     * Dataprovider provide filters and results in JSON format when searching for hotel
     * @return
     * @throws IOException
     */
    @DataProvider(name = "Search_Hotels")
    public static Object[][] searchHotels() throws IOException {

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

    /**
     * Dataprovider provide filters and results as Java Objects when searching for hotel
     * @return
     * @throws IOException
     */
    @DataProvider(name = "Search_Object_Hotels")
    public static Object[][] searchObjectHotels() throws IOException {
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

    /**
     * Dataprovider provide filters and results as Java Objects when searching for hotel
     * @return
     * @throws IOException
     */
    @DataProvider(name = "FeatureHotelTestDataProvider")
    public static Object[][] featureHotelTestDataProvider() throws IOException {
        File fileSearchDataDriven = new File("src/test/resources/data/login_success/e2e_featured_hotel_tests.json");
        ObjectMapper mapper = new ObjectMapper();

        JSONArray testData = new JSONArray(Files.readFile(fileSearchDataDriven));
        Object [][] array = new Object[testData.length()][];

        for (int i = 0; i < testData.length(); i++) {

            JSONObject data = (JSONObject) testData.get(i);

            Account account = mapper.readValue(data.getJSONObject("account").toString(), Account.class);
            HotelSearchFilter filter = mapper.readValue(data.getJSONObject("filters").toString(), HotelSearchFilter.class);
            List<HotelCard> hotelCards = mapper.readValue(data.getJSONArray("results").toString(), new TypeReference<List<HotelCard>>() {});

            Object [] tc = new Object[]{account, filter, hotelCards};
            array[i] = tc;
        }

        return array;
    }

    /**
     * Dataprovider provide filters and results as Java Objects when searching for hotel
     * @return
     * @throws IOException
     */
    @DataProvider(name = "FilterResult")
    public static Object[][] filterResult() throws IOException {
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

    @DataProvider (name = "Filter_Result")
    public Object[][] filterResult1 () throws IOException {
        File fileData = new File("src/test/resources/data/feature_hotels.json");
        ObjectMapper mapper = new ObjectMapper();

        List<FilterResult> filterResults = mapper.readValue(fileData, new TypeReference<List<FilterResult>>() {});
        Object[][] array = new Object[filterResults.size()][];

        for (int i = 0; i< filterResults.size(); i++){
            array[i] = new Object[]{filterResults.get(i)};
        }

        return array;
    }
}
