package pl.seleniumdemo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement chekInInput;

    @FindBy(name = "checkout")
    private WebElement chekOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//*[@id='li_myaccount']/ul/li[2]")
    private List<WebElement> mySignUpLink;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates(String checkin, String checkout) {
        chekInInput.sendKeys(checkin);
        chekOutInput.sendKeys(checkout);
    }

    public void setTravellers(int adultstoAdd, int childToAdd) {
        travellersInput.click();
        addTraveller(adultPlusBtn,adultstoAdd);
        addTraveller(childPlusBtn,childToAdd);
    }

    private void addTraveller(WebElement travelerBtn, int numberOfTravellers) {
        for (int i = 0; i < numberOfTravellers; i++) {
            travelerBtn.click();
        }
    }

        public void performSearch() {searchButton.click();}

    public void openSignUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        mySignUpLink.get(1).click();
    }
}
