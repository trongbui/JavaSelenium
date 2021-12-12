package com.itms.training.java.utils;

import org.json.JSONObject;

import java.util.List;

public class TestAssert {

    public static boolean isHotelJsonEqual(JSONObject actualHotelCard, JSONObject expectedHotelCard) {
        return actualHotelCard.getString("name").equals(expectedHotelCard.getString("name"))
                && actualHotelCard.getString("location").equals(expectedHotelCard.getString("location"))
                && actualHotelCard.getDouble("price") == expectedHotelCard.getDouble("price")
                && actualHotelCard.getInt("stars") == expectedHotelCard.getInt("stars");
    }

    public static boolean isHotelJsonListEqual(List<JSONObject> actualHotelCards, List<JSONObject> expectedHotelCards) {

        if (actualHotelCards.size() != expectedHotelCards.size()) {
            return false;
        }

        for (int i = 0; i < actualHotelCards.size(); i++) {
            if (!isHotelJsonInclude(actualHotelCards.get(i), expectedHotelCards)) {
                System.out.println(actualHotelCards.get(i));
                return false;
            }
        }

        return true;
    }

    public static boolean isHotelJsonInclude(JSONObject hotelCard, List<JSONObject> hotelCardList) {
        boolean include = false;
        for (int i = 0; i < hotelCardList.size(); i++) {
            if (isHotelJsonEqual(hotelCard, hotelCardList.get(i))) {
                include = true;
                break;
            }
        }
        return include;
    }
}
