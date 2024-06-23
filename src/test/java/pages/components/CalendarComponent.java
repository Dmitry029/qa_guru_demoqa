package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement monthSelector = $(".react-datepicker__month-select");
    private final SelenideElement yearSelector = $(".react-datepicker__year-select");

    public void setDateOfBirthBySelect(String date) {
        List<String> splitDate = Arrays.asList(date.split("\\s|,"));
        dateOfBirthInput.click();
        monthSelector.selectOption(splitDate.get(1));
        yearSelector.selectOption(splitDate.get(2));
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", splitDate.get(0)))
                .click();
    }

    public void setDateOfBirthByType(String date) {
        dateOfBirthInput.click();
        dateOfBirthInput.sendKeys(Keys.CONTROL + "a");
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
    }
}
