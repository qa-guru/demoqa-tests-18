package demoqa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import demoqa.pages.RegistrationPage;

import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationWithFakerTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        Faker faker = new Faker(new Locale("it"));

        String userName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
//                userNumber = "+7" + faker.number().numberBetween(1000000, 9999999);
//                currentAddress = faker.address().fullAddress();
                currentAddress = faker.lebowski().quote();

        registrationPage.openPage()
                .setFirstName(userName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender("Other")
                .setPhone("1234567890")
                .setBirthDate("30", "July", "2008");

        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", userName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", "Other")
                .verifyResult("Mobile", "1234567890")
                .verifyResult("Date of Birth", "30 July,2008")
                .verifyResult("Current address", currentAddress);
//        registrationPage.registrationResultsModal.verifyResult("Student Name", userName + " Egorov");
    }

    @Test
    void successfulRegistration1Test() {
        String userName = "Alex";

        registrationPage.openPage();

        registrationPage.setFirstName(userName);
        registrationPage.setLastName("Egorov");
        registrationPage.setEmail("alex@egorov.com");
        registrationPage.setGender("Other");
        registrationPage.setPhone("1234567890");

        $("#dateOfBirthInput").click();
        // ...
    }

}
