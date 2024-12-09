package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        //ustawienie generowania unikalnych adresach email
        int randomNumber = (int) (Math.random() * 1000);
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

    @Test
    public void signUpEmptyFieldsTest() {

            //otwarcie strony
            driver.findElements(By.xpath("//*[@id='li_myaccount']"))
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .ifPresent(WebElement::click);
            driver.findElements(By.xpath("//*[@id='li_myaccount']/ul/li[2]")).get(1).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='alert alert-danger']//p")));

            List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                    .stream().
                    map(WebElement::getText)
                    .collect(Collectors.toList());

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='alert alert-danger']//p")));

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(errors.contains("The Email field is required."));
            softAssert.assertTrue(errors.contains("The Password field is required."));
            softAssert.assertTrue(errors.contains("The Password field is required."));
            softAssert.assertTrue(errors.contains("The First name field is required."));
            softAssert.assertTrue(errors.contains("The Last name field is required."));
            softAssert.assertAll();

        }

    @Test
    public void signUpWithInvalidEmailTest() {

            //znajdowanie przycisku sign up i klikniecie
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
            driver.findElement(By.name("email")).sendKeys("test.pl");
            driver.findElement(By.name("password")).sendKeys("Testowe123@");
            driver.findElement(By.name("confirmpassword")).sendKeys("Testowe123@");
            WebElement elementSignUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Sign Up']")));

            driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

            List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='alert alert-danger']//p")));

            Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));

    }
}

