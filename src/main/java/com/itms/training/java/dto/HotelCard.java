package com.itms.training.java.dto;

public class HotelCard {

    private String name;
    private String location;
    private int stars;
    private int ratings;
    private String currency;
    private double price;
    private int nights;

    public HotelCard() {
    }

    public HotelCard(String name, String location, int stars, int ratings, String currency, double price, int nights) {
        this.name = name;
        this.location = location;
        this.stars = stars;
        this.ratings = ratings;
        this.currency = currency;
        this.price = price;
        this.nights = nights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    @Override
    public String toString() {
        return "HotelCard{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", stars=" + stars +
                ", ratings=" + ratings +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                ", nights=" + nights +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        HotelCard hotelCard = (HotelCard) o;
        return this.getName().equals(hotelCard.getName())
                && this.getLocation().equals(hotelCard.getLocation())
                && this.getStars() == hotelCard.getStars()
                && this.getCurrency().equals(hotelCard.getCurrency())
                && this.getPrice() == hotelCard.getPrice();
    }

    public boolean compare(HotelCard hotelCard) {
        return this.getName().equals(hotelCard.getName())
                && this.getLocation().equals(hotelCard.getLocation())
                && this.getStars() == hotelCard.getStars()
                && this.getCurrency() == hotelCard.getCurrency()
                && this.getPrice() == hotelCard.getPrice();
    }
}
