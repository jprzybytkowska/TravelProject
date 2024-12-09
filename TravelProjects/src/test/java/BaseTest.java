import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        //konfiguracja webdrivera dla chroma
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //otwarcie strony
        driver.get("http://www.kurs-selenium.pl/demo/");

    }
    @AfterMethod
    public void teearDown() {
        driver.quit();
    }

}
