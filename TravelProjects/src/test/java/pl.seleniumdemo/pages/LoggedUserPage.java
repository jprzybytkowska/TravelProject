package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import static pl.seleniumdemo.utils.SeleniumHelper.waitForWebElementToBeVisible;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;

    private WebDriver driver;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeadingText() {
        waitForWebElementToBeVisible(driver, heading);
        return heading.getText();
    }
}
