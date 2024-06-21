package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;
import pages.components.ResultOfFillingOutTheFormComponent;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    ResultOfFillingOutTheFormComponent resultOfFillingOutTheFormComponent = new ResultOfFillingOutTheFormComponent();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
