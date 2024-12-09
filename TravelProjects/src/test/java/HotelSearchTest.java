import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        //znalezienie pola do wpisania kraju, klikniecie
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        //wpisanie nazwy kraju
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        //wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wybor z listy podpowiedzi dla kraju
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        //wpisanie daty od i klikniecie
        driver.findElement(By.name("checkin")).sendKeys("12/12/2024");
        driver.findElement(By.name("checkout")).click();
        //wpisanie daty do
        driver.findElements(By.xpath("//td[@class='day ' and text()='18']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        //ustawianie ilosci osob
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        //wyszukiwanie i pobieranie nazw hoteli
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        //sprawdzenie wynikow na stronie
        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

    }

    @Test
    public void searchHotelWithoutNameTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wpisanie daty od i klikniecie
        driver.findElement(By.name("checkin")).sendKeys("22/12/2024");
        driver.findElement(By.name("checkout")).click();
        //wpisanie daty do
        driver.findElements(By.xpath("//td[@class='day ' and text()='25']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        //ustawianie ilosci osob
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        //wyszukiwanie i pobieranie nazw hoteli
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        //odnalezienie komunikatu 'NoResultFonuds'
        WebElement noResultsFound = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));

        Assert.assertTrue(noResultsFound.isDisplayed());
        Assert.assertEquals(noResultsFound.getText(),"No Results Found");

    }
}