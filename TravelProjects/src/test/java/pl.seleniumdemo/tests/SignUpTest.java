package pl.seleniumdemo.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    private WebDriverWait wait;

    @Test
    public void signUpTest() {

        String lastName = "Testerka";
        //ustawienie generowania unikalnych adresach email
        int randomNumber = (int) (Math.random() * 1000);

        //znajdowanie przycisku sign up i klikniecie
        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Justyna")
                .setLastName(lastName)
                .setPhone("123456789")
                .setEmail("tester" + randomNumber + "@testerka.pl")
                .setPassword("Testowe123@")
                .confirmPassword("Testowe123@")
                .signUp();
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Justyna Testerka");
    }

//    @Test
//    public void signUpTest2() { //inny sposob wypelniania formularza
//
//        //ustawienie generowania unikalnych adresach email
//        int randomNumber = (int) (Math.random() * 1000);
//        String email = "tester" + randomNumber + "@testerka.pl";
//
//        User user = new User();
//        user.setFirstName("Justyna");
//        user.setLastName("Testerkaa");
//        user.setPhone("1234569870");
//        user.setEmail(email);
//        user.setPassword("Testowe1122");
//
//        //znajdowanie przycisku sign up i klikniecie
//        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
//        hotelSearchPage.openSignUpForm();
//
//        //wypelnienie formularza
//        SignUpPage signUpPage = new SignUpPage(driver)
//                .fillSignUpForm(user);
//
//        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
//
//        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
//        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Justyna Testerkaa");
//    }

    @Test
    public void signUpEmptyFieldsTest() {

        //otwarcie strony
        //znajdowanie przycisku sign up i klikniecie

        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.signUp();

        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }

    @Test
    public void signUpWithInvalidEmailTest() {

        //wypelnienie formularza
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Justyna")
                .setLastName("Testerkaa")
                .setPhone("123456789")
                .setEmail("email")
                .setPassword("Testowe123@")
                .confirmPassword("Testowe123@");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}

