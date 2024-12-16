package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("22/12/2024", "28/12/2024")
                .setTravellers(0, 1)
                .performSearch().getHotelNames();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

    }

    @Test
    public void searchHotelWithoutNameTest() {

        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("22/12/2024", "25/12/2024")
                .setTravellers(0, 1)
                .performSearch();

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

    }
}
