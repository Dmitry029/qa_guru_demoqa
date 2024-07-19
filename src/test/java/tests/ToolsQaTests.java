package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CalendarComponent;
import pages.components.ResultOfFillingOutTheFormComponent;
import utils.DateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestData.*;

public class ToolsQaTests extends BaseTest {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final ResultOfFillingOutTheFormComponent resultOfFillingOutTheFormComponent =
            new ResultOfFillingOutTheFormComponent();
    private final CalendarComponent calendarComponent = new CalendarComponent();

    @Test
    @Tag("regression")
    @DisplayName("Заполнение всех полей формы")
    void fillOutAllFormFieldsTest() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String email = getEmail();
        String gender = getGender();
        String mobile = getMobile();
        String dateOfBirth = getDateOfBirth();
        String subject = getSubject();
        String hobby = getHobby();
        String fileName = getFile();
        String address = getAddress();
        String state = getState();
        String city = getCity(state);

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
    @Tag("smoke")
    @DisplayName("Заполнение только обязательных полей формы")
    void fillInOnlyTheRequiredFormFieldsTest() {

        String firstName = getFirstName();
        String lastName = getLastName();
        String gender = getGender();
        String mobile = getMobile();

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
    @Tag("regression")
    @Tag("negative")
    @DisplayName("Негативный тест. Заполнение не всех обязательных полей формы")
    void notAllRequiredDataTest() {
        String firstName = getFirstName();
        String gender = getGender();
        String mobile = getMobile();

        registrationPage.openPage()
                .setFirstName(firstName)
                .selectGender(gender)
                .setMobile(mobile)
                .submitRegistration();
        registrationPage.checkThereIsNoConfirmationWindow();
    }

}