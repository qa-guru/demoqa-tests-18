package demoqa.tests;

import org.junit.jupiter.api.Test;
import demoqa.pages.RegistrationPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static demoqa.utils.RandomUtils.*;

public class RegistrationWithRandomUtilsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        String[] genders = {"Male", "Female", "Other"};

        String userName = getRandomString(10),
                lastName = getRandomString(10),
                userEmail = getRandomEmail(),
                gender = getRandomItemFromArray(genders);

        registrationPage.openPage()
                .setFirstName(userName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhone("1234567890")
                .setBirthDate("30", "July", "2008");

        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", userName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", "1234567890")
                .verifyResult("Date of Birth", "30 July,2008");
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
