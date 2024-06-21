package pages.components;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;

public class ResultOfFillingOutTheFormComponent {
    public void checkFormIsFilledOutCorrectly(List<String> expectedData) {
        $$("tbody tr td:last-child")
                .should(texts(expectedData));
    }

    public boolean isFormFilledOutCorrectly(List<String> expectedData) {
        List<String> l = $$("tbody tr td:last-child")
                .texts().stream()
                .filter(e -> e.length() > 2).toList();
        return expectedData.equals(l);
    }
}
