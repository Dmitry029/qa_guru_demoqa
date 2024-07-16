package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Open form")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('close-fixedban').parentNode.remove()");
        executeJavaScript("document.getElementsByTagName('footer')[0].remove()");
        return this;
    }

    @Step("Set first name")
    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Set last name")
    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Set email")
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Select gender")
    public RegistrationPage selectGender(String gender) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", gender))
                .should(visible).click();
        return this;
    }

    @Step("Set mobile")
    public RegistrationPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
        return this;
    }

    @Step("Select subject")
    public RegistrationPage selectSubject(String subject) {
        subjectsContainer.click();
        subjectInput.sendKeys(subject);
        $x(String.format("//*[@id and text()='%s']", subject)).click();
        return this;
    }

    @Step("Select hobbies")
    public RegistrationPage selectHobbies(String hobby) {
        $x(String.format("//*[contains(@class, 'custom-control-label') and text()='%s']", hobby))
                .scrollIntoView(true).click();
        return this;
    }

    @Step("Select hobbies")
    public RegistrationPage setPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Set address")
    public RegistrationPage setAddress(String address) {
        addressInput.click();
        addressInput.setValue(address);
        return this;
    }

    @Step("Select state")
    public RegistrationPage selectState(String state) {
        stateInput.scrollIntoView(true).click();
        $x(String.format("//*[@id and text()='%s']", state)).click();
        return this;
    }

    @Step("Select city")
    public RegistrationPage selectCity(String city) {
        cityInput.click();
        $x(String.format("//*[@id and text()='%s']", city)).click();
        return this;
    }

    @Step("Submit registration")
    public void submitRegistration() {
        submitButton.scrollIntoView(true).click();
    }

    @Step("Check there is no confirmation window")
    public void checkThereIsNoConfirmationWindow() {
        confirmationWindow.shouldNotBe(visible);
    }
}
