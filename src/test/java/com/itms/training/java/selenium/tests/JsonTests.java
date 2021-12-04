package com.itms.training.java.selenium.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itms.training.java.dto.HotelCard;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTests {

    @Test
    public void jsonTests() {
        JSONObject jo = new JSONObject();

        jo.put("name", "Rendezvous Hotels");
        jo.put("location", "Singapore");
        jo.put("stars", 2);
        jo.put("ratings", 2);
        jo.put("price", 93.50);
        jo.put("nights", 2);

        System.out.println(jo);

        System.out.println(jo.get("name"));
        System.out.println(jo.getString("name"));
        System.out.println(jo.getInt("stars"));
        System.out.println(jo.getDouble("price"));

    }

    @Test
    public void mapToJsonTests() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");

        JSONObject jo = new JSONObject(map);

        System.out.println(map);
        System.out.println(jo);
    }

    @Test
    public void stringToJsonTest() {
        String strJson = "{\"ratings\":2,\"price\":93.5,\"nights\":2,\"name\":\"Rendezvous Hotels\",\"location\":\"Singapore\",\"stars\":2}";

        JSONObject jo = new JSONObject(strJson);

        System.out.println(strJson);
        System.out.println(jo);
    }

    @Test
    public void serializeJavaObjectToJson() {
        HotelCard hotelCard = new HotelCard();
        hotelCard.setName("Rendezvous Hotels");
        hotelCard.setLocation("Singapore");
        hotelCard.setStars(2);
        hotelCard.setRatings(2);
        hotelCard.setPrice(93.50);
        hotelCard.setNights(2);

        JSONObject jo = new JSONObject(hotelCard);

        System.out.println(hotelCard.toString());
        System.out.println(jo);

        System.out.println(hotelCard.getPrice());
        System.out.println(jo.getDouble("price"));
    }

    @Test
    public void jsonToJavaObject() throws IOException {
        String strJson = "{\"ratings\":2,\"price\":93.5,\"nights\":2,\"name\":\"Rendezvous Hotels\",\"location\":\"Singapore\",\"stars\":2}";
        ObjectMapper objectMapper = new ObjectMapper();

        HotelCard actualHotelCard = objectMapper.readValue(strJson, HotelCard.class);

        File file = new File("src/test/resources/data/hotel_card.json");
        HotelCard expectedHotelCard = objectMapper.readValue(file, HotelCard.class);

        Assert.assertEquals(actualHotelCard, expectedHotelCard);
    }

    @Test
    public void readJsonFile() throws IOException {
        File file = new File("src/test/resources/data/hotel_card.json");

        ObjectMapper objectMapper = new ObjectMapper();
        HotelCard hotelCard = objectMapper.readValue(file, HotelCard.class);

        System.out.println(hotelCard.toString());

        JSONObject jsonObject = new JSONObject(hotelCard);
        System.out.println(jsonObject);
    }

    @Test
    public void readJsonFileToJson() throws IOException {
        File fJson = new File("src/test/resources/data/hotel_card.json");
        File fJsonArray = new File("src/test/resources/data/hotel_cards.json");
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject jsonObject = new JSONObject(Files.readFile(fJson));
        JSONArray jsonArray = new JSONArray(Files.readFile(fJsonArray));

        System.out.println(jsonObject);
        System.out.println(jsonArray.get(0));

    }

    @Test
    public void jsonArrayTest() throws IOException {
        File file = new File("src/test/resources/data/hotel_cards.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<HotelCard> hotelCardList = objectMapper.readValue(file, new TypeReference<List<HotelCard>>() {
        });

        for (HotelCard hotelCard: hotelCardList) {
            System.out.println(hotelCard.toString());

            JSONObject jsonObject = new JSONObject(hotelCard);
            System.out.println(jsonObject);
        }
    }
}
