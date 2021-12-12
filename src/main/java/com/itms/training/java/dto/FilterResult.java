package com.itms.training.java.dto;

import java.util.List;

public class FilterResult {

    HotelSearchFilter filters;
    List<HotelCard> results;

    public FilterResult(){}

    public HotelSearchFilter getFilters() {
        return filters;
    }

    public List<HotelCard> getResults() {
        return results;
    }
}
