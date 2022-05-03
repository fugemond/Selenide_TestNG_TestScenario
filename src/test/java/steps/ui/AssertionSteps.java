package steps.ui;

import com.codeborne.selenide.Condition;
import core.interfaces.PageElement;
import lombok.extern.log4j.Log4j2;

import static steps.ui.AllureSteps.assertThatCurrentUrlIs;
import static steps.ui.AllureSteps.assertThatElementContainsValue;
import static steps.ui.AllureSteps.verifyElementCondition;

@Log4j2
public class AssertionSteps {

    public static void VERIFY_ELEMENT_CONDITION(PageElement pageElement, Condition condition) {
        verifyElementCondition(pageElement.getElementName(), condition);
    }

    public static void ASSERT_THAT_ELEMENT_CONTAINS_VALUE(PageElement pageElement, String text) {
        assertThatElementContainsValue(pageElement.getElementName(), text);
    }

    public static void ASSERT_THAT_CURRENT_URL_IS(String url) {
        assertThatCurrentUrlIs(url);
    }
}
