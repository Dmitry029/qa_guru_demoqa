package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CalendarComponent;
import pages.components.ResultOfFillingOutTheFormComponent;
import utils.DateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToolsQaTests extends BaseTest {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final ResultOfFillingOutTheFormComponent resultOfFillingOutTheFormComponent =
            new ResultOfFillingOutTheFormComponent();
    private final CalendarComponent calendarComponent = new CalendarComponent();

    @Test
    void fillOutAllFormFieldsTest() {
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String email = "test@test.com";
        String gender = "Male";
        String mobile = "0123456789";
        String dateOfBirth = "01 May,1999";
        String subject = "Maths";
        String hobby = "Sports";
        String fileName = "img.png";
        String address = "Test street 1";
        String state = "Haryana";
        String city = "Karnal";

        List<String> expectedData = List.of(
                firstName + " " + lastName,
                email,
                gender,
                mobile,
                dateOfBirth,
                subject,
                hobby,
                fileName,
                address,
                state + " " + city
        );

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(gender)
                .setMobile(mobile);
        calendarComponent.setDateOfBirthBySelect(dateOfBirth);
        registrationPage.selectSubject(subject)
                .selectHobbies(hobby)
                .setPicture(fileName)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitRegistration();
        resultOfFillingOutTheFormComponent.checkFormIsFilledOutCorrectly(expectedData);
    }

    @Test
    void fillInOnlyTheRequiredFormFieldsTest() {
        String firstName = "TestFirstName2";
        String lastName = "TestLastName2";
        String gender = "Male";
        String mobile = "0123456789";

        List<String> expectedData = List.of(
                firstName + " " + lastName,
                gender,
                mobile,
                DateUtils.getCurrentDate()
        );

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setMobile(mobile)
                .submitRegistration();
        assertTrue(resultOfFillingOutTheFormComponent.isFormFilledOutCorrectly(expectedData));
    }

    @Test
    void notAllRequiredDataTest() {
        String firstName = "TestFirstName2";
        String gender = "Male";
        String mobile = "0123456789";

        registrationPage.openPage()
                .setFirstName(firstName)
                .selectGender(gender)
                .setMobile(mobile)
                .submitRegistration();
        registrationPage.checkThereIsNoConfirmationWindow();
    }
}