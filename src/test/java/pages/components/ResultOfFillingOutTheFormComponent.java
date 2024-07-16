package pages.components;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;

public class ResultOfFillingOutTheFormComponent {

    private final ElementsCollection tableData = $$("tbody tr td:last-child");

    @Step("Check form is filled out correctly")
    public void checkFormIsFilledOutCorrectly(List<String> expectedData) {
        tableData.should(texts(expectedData));
    }

    @Step("Is form filled out correctly")
    public boolean isFormFilledOutCorrectly(List<String> expectedData) {
        List<String> data = tableData
                .texts().stream()
                .filter(e -> e.length() > 2).toList();
        return expectedData.equals(data);
    }
}
