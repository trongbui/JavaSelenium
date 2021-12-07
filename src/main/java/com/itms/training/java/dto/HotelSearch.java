package com.itms.training.java.dto;

public class HotelSearch {

    String cityName;
    String checkInDate;
    String checkOutDate;
    int rooms;
    int adults;
    int[] childs;
    String nationality;

    public HotelSearch(String cityName, String checkInDate, String checkOutDate, int rooms, int adults, int[] childs, String nationality) {
        this.cityName = cityName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        this.adults = adults;
        this.childs = childs;
        this.nationality = nationality;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int[] getChilds() {
        return childs;
    }

    public void setChilds(int[] childs) {
        this.childs = childs;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}