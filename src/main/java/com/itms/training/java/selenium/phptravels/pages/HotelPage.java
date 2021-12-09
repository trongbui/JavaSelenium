package com.itms.training.java.selenium.phptravels.pages;

import com.itms.training.java.dto.HotelCard;
import com.itms.training.java.dto.HotelSearchFilter;
import com.itms.training.java.selenium.phptravels.pages.components.FeaturedHotelInfo;
import com.itms.training.java.selenium.phptravels.pages.components.SearchHotelCardInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelPage extends BasePage{

    @FindBy(id="checkin")
    private WebElement tbCheckIn;

    @FindBy(id="checkout")
    private WebElement tbCheckOut;

    @FindBy(css = "a.dropdown-btn.travellers")
    private WebElement lkTravellers;

    @FindBy(id = "rooms")
    private WebElement tbRooms;

    @FindBy(id = "adults")
    private WebElement tbAdults;

    @FindBy(id = "childs")
    private WebElement tbChilds;

    @FindBy(id = "nationality")
    private WebElement slNationality;

    @FindBy(xpath = "//button[normalize-space(.)='Search']")
    private WebElement btnSearch;

    @FindBy(xpath = "//div[@class='owl-stage']")
    private WebElement elFeaturedHotelList;

    public HotelPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchHotelPage quickSearch(String cityName, String checkInDate, String checkOutDate, int rooms, int adults, int[] childs, String nationality) throws ParseException {
        specifyCityName(cityName);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setTravellers(rooms, adults, childs, nationality);
//        click(btnSearch);

        return new SearchHotelPage(webDriver);
    }

    public SearchHotelPage quickSearch(HotelSearchFilter filter) throws ParseException {
        return quickSearch(
                filter.getCityName(),
                filter.getCheckInDate(),
                filter.getCheckOutDate(),
                filter.getRooms(),
                filter.getAdults(),
                filter.getChilds(),
                filter.getNationality()
        );
    }

    public List<HotelCard> getFeaturedHotelList() {
        List<WebElement> hotelContainers = elFeaturedHotelList.findElements(By.xpath(".//div[@class='owl-item active']/div[contains(@class, 'card-item')]"));
        List<HotelCard> hotelCards = new ArrayList<>();

        for (WebElement container : hotelContainers) {
            FeaturedHotelInfo featuredHotelInfo = new FeaturedHotelInfo(webDriver, container);
            hotelCards.add(featuredHotelInfo.getHotelCard());
        }

        return hotelCards;
    }

    private void specifyCityName(String cityName) {
        By bySl2HotelCityContainer = By.id("select2-hotels_city-container");
        By byTbHotelCityResult = By.xpath("//input[@aria-controls='select2-hotels_city-results']");
        By byLinkCity = By.xpath(String.format("//li[contains(normalize-space(.), '%s')]", cityName));
        By byLoadingResult = By.xpath("//li[@class='select2-results__option loading-results']");

        click(bySl2HotelCityContainer);
        inputText(byTbHotelCityResult, cityName);

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(byLoadingResult));
        click(byLinkCity);
    }

    /***
     *
     * @param date: "dd/MM/yyyy
     * @throws ParseException
     */
    public void selectCheckInDate(String date) throws ParseException {
        Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

        scrollClick(tbCheckIn);
        String xpathDay = String.format("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'display: block;')]//td[contains(@class, 'day  new') and .='%s']", checkInDate.getDay());
        WebElement day = webDriver.findElement(By.xpath(xpathDay));
        scrollClick(day);
    }

    /***
     *
     * @param date: "dd/MM/yyyy
     * @throws ParseException
     */
    // 03/12/2021 -> 03-12-2021
    private void setCheckInDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String checkinDate = new SimpleDateFormat("dd-MM-yyyy").format(d);
        setText(tbCheckIn, checkinDate);
    }

    /**
     *
     * @param date
     * @throws ParseException
     */
    private void setCheckOutDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String checkoutDate = new SimpleDateFormat("dd-MM-yyyy").format(d);
        setText(tbCheckOut, checkoutDate);
    }

    private void setTravellers(int rooms, int adults, int[] childs, String nationality) {
        click(lkTravellers);

        setRooms(rooms);
        setAdults(adults);
        setChilds(childs);
        selectNationality(nationality);

        click(lkTravellers);
    }

    private void setRooms(int rooms) {
        setText(tbRooms, String.valueOf(rooms));
    }

    private void setAdults(int adults) {
        setText(tbAdults, String.valueOf(adults));
    }

    private void setChilds(int[] childs) {
        WebElement elChildsContainer = webDriver.findElement(By.xpath("//div[@class='dropdown-item'][.//input[@id='childs']]"));
        selectQuantity(elChildsContainer, childs.length);

        for (int i = 0; i < childs.length; i++) {
            int j = i + 1;
            By byAge = By.id("ages" + j);
            Select slAge = new Select(
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(byAge)));
            slAge.selectByVisibleText(String.valueOf(childs[i]));
        }
    }

    private void selectNationality(String nationality) {
        Select sl = new Select(slNationality);
        sl.selectByVisibleText(nationality);
    }

    private void selectQuantity(WebElement elRoot, int qty){
        WebElement elQtyDec = elRoot.findElement(By.xpath(".//div[@class='qtyDec']"));
        WebElement elQty = elRoot.findElement(By.tagName("input"));
        WebElement elQtyInc = elRoot.findElement(By.xpath(".//div[@class='qtyInc']"));

        int currentQty = Integer.parseInt(elQty.getAttribute("value"));

        while (currentQty != qty) {
            if (currentQty < qty) {
                click(elQtyInc);
                currentQty = Integer.parseInt(elQty.getAttribute("value"));
            } else {
                click(elQtyDec);
                currentQty = Integer.parseInt(elQty.getAttribute("value"));
            }
        }
    }
}
