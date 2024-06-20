package tests;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ToolsQaTests extends BaseTest {

    @Test
    void fillForm() {
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String email = "test@test.com";
        String gender = "Male";
        String mobile = "0123456789";
        String dateOfBirth = "20 May,1999";
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
                .setMobile(mobile)
                .setDateOfBirth(dateOfBirth)
                .selectSubject(subject)
                .selectHobbies(hobby)
                .setPicture(fileName)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitRegistration()
                .checkFormIsFilledOutCorrectly(expectedData);
    }
}