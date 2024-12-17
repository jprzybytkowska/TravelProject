package pl.seleniumdemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        //konfiguracja webdrivera dla chroma
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        //otwarcie strony
        driver.get("http://www.kurs-selenium.pl/demo/");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void teearDown() {
        driver.quit();
    }

}
