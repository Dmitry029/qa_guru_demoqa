package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CalendarComponent;
import pages.components.ResultOfFillingOutTheFormComponent;
import utils.DateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.TestData.*;

public class ToolsQaTests extends BaseTest {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final ResultOfFillingOutTheFormComponent resultOfFillingOutTheFormComponent =
            new ResultOfFillingOutTheFormComponent();
    private final CalendarComponent calendarComponent = new CalendarComponent();

    @Test
    void fillOutAllFormFieldsTest() {

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
                .setPicture("img/" + fileName)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitRegistration();
        resultOfFillingOutTheFormComponent.checkFormIsFilledOutCorrectly(expectedData);
    }

    @Test
    void fillInOnlyTheRequiredFormFieldsTest() {

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
        registrationPage.openPage()
                .setFirstName(firstName)
                .selectGender(gender)
                .setMobile(mobile)
                .submitRegistration();
        registrationPage.checkThereIsNoConfirmationWindow();
    }
}