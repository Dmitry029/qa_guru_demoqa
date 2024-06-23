package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail-wrapper input");
    private final SelenideElement mobileInput = $("#userNumber");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement addressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement confirmationWindow = $(".modal-content");
    private final SelenideElement subjectsContainer = $("#subjectsContainer");
    private final SelenideElement subjectInput = $("[class*= 'subjects-auto-complete__control--is-focused'] input");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", gender))
                .should(visible).click();
        return this;
    }

    public RegistrationPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
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
        subjectsContainer.click();
        subjectInput.sendKeys(subject);
        $x(String.format("//*[@id and text()='%s']", subject)).click();
        return this;
    }

    public RegistrationPage selectHobbies(String hobby) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", hobby))
                .scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage setPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.click();
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage selectState(String state) {
        stateInput.scrollIntoView(true).click();
        $x(String.format("//*[@id and text()='%s']", state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        cityInput.click();
        $x(String.format("//*[@id and text()='%s']", city)).click();
        return this;
    }

    public void submitRegistration() {
        submitButton.scrollIntoView(true).click();
    }

    public void checkThereIsNoConfirmationWindow() {
        confirmationWindow.shouldNotBe(visible);
    }
}
