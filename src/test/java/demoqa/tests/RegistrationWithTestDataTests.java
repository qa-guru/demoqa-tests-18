package demoqa.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import demoqa.pages.RegistrationPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationWithTestDataTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

//    String userName = "Alex",
//            lastName = "Egorov",
//            userEmail = "alex@egorov.com";

//    static String userName,
//            lastName,
//            userEmail;

    @BeforeEach
    void prepareTestData() {
//        userName = "Alex";
//        lastName = "Egorov";
//        userEmail = "alex@egorov.com";
    }

    @Test
    void successfulRegistrationTest() {
//        String userName = "Alex",
//                lastName = "Egorov",
//                userEmail = "alex@egorov.com";

        registrationPage.openPage()
                .setFirstName(TestData.userName)
                .setLastName(TestData.lastName)
                .setEmail(TestData.userEmail)
                .setGender("Other")
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
                .verifyResult("Student Name", TestData.userName + " " + TestData.lastName)
                .verifyResult("Student Email", TestData.userEmail)
                .verifyResult("Gender", "Other")
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
