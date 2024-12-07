import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {


    @Test
    public void signUp() {
        //konfiguracja webdrivera dla chroma
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //otwarcie strony
        driver.get("http://www.kurs-selenium.pl/demo/");

        int randomNumber = (int) (Math.random()*1000);
        String email = "tester" + randomNumber + "@tester.pl";

        //znajdowanie przycisku sign up i klikniecie - PROBLEM

        driver.findElements(By.xpath("//*[@id='li_myaccount']"))
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//*[@id='li_myaccount']/ul/li[2]")).get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wypelnienie formularza
        driver.findElement(By.name("firstname")).sendKeys("Justyna");
        driver.findElement(By.name("lastname")).sendKeys("Testerka");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Testowe123@");
        driver.findElement(By.name("confirmpassword")).sendKeys("Testowe123@");
        WebElement elementSignUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Sign Up']")));

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement elementLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains("Hi, Justyna Testerka"), "Nie znaleziono powitania");
        //Assert.assertEquals("", heading.getText());

    }
}
