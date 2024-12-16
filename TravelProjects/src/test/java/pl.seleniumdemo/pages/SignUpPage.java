package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.bouncycastle.cms.RecipientId.password;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //WebElement elementSignUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Sign Up']")));

    //WebElement elementLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

    public SignUpPage setFirstName(String firstname) {
        firstNameInput.sendKeys(firstname);
        return this;
    }

    public SignUpPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPhone(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage confirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public LoggedUserPage signUp() {
        signUpButton.click();
        return new LoggedUserPage(driver);
    }

    public List<String> getErrors() {
        return errors.stream().
                map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
