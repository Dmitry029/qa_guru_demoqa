package pages;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        $("#firstName").setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        $("#userEmail-wrapper input").setValue(email);
        $x("//*[contains(@class, 'custom-control-label') and text()='Male']")
                .should(visible).click();
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", gender))
                .should(visible).click();
        return this;
    }

    public RegistrationPage setMobile(String mobile) {
        $("#userNumber").setValue(mobile);
        return this;
    }

    public RegistrationPage setDateOfBirth(String date) {
        String locator = "#dateOfBirthInput";
        $(locator).click();
        $(locator).sendKeys(Keys.CONTROL + "a");
        $(locator).sendKeys(date);
        $(locator).sendKeys(Keys.ENTER);
        return this;
    }

    public RegistrationPage selectSubject(String subject) {
        $("#subjectsContainer").click();
        $("[class*= 'subjects-auto-complete__control--is-focused'] input")
                .sendKeys(subject);
        $x(String.format("//*[@id and text()='%s']", subject)).click();
        return this;
    }

    public RegistrationPage selectHobbies(String hobby) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", hobby))
                .scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage setPicture(String fileName) {
        $("#uploadPicture").uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        String locator = "#currentAddress";
        $(locator).click();
        $(locator).setValue(address);
        return this;
    }

    public RegistrationPage selectState(String state) {
        $("#state").scrollIntoView(true).click();
        $x(String.format("//*[@id and text()='%s']", state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        $("#city").click();
        $x(String.format("//*[@id and text()='%s']", city)).click();
        return this;
    }

    public void submitRegistration() {
        $("#submit").scrollIntoView(true).click();
    }

    public void checkThereIsNoConfirmationWindow() {
        $(".modal-content").shouldNotBe(visible);
    }
}
