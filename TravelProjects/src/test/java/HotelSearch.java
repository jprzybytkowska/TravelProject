import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearch {


    @Test
    public void searchHotel() {
        //konfiguracja webdrivera dla chroma
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //otwarcie strony
        driver.get("http://www.kurs-selenium.pl/demo/");
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
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }
}
