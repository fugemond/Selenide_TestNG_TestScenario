package steps.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utility.PropertiesLoader;

import static com.codeborne.selenide.Selenide.open;
import static core.TestScenario.getTestScenario;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Steps {

    public static void OPEN_PAGE_BY_LINK(String pageName, String url){
        String urlToOpen = PropertiesLoader.tryLoadProperty(url);
        open(urlToOpen);
        getTestScenario().setCurrentPage(getTestScenario().getPage(pageName));
    }

    public void pageIsOpened(String pageName){
        getTestScenario().setCurrentPage(getTestScenario().getPage(pageName));
    }
    public static void CLICK_ON_ELEMENT(String elementName){
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementName);
        element.shouldHave(Condition.visible).click();
    }
    public static void ENTER_VALUE_INTO_FIELD(String inputText, String elementName){
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementName);
        element.sendKeys(inputText);
    }

    public static void PAGE_IS_LOADED(String pageName){
        getTestScenario().setCurrentPage(getTestScenario().getPage(pageName));
    }

    public void verifyThatElementCondition(String elementName, Condition condition){
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementName);
        assertThat("zalupa",element.shouldBe(condition).isDisplayed(),equalTo(true));
    }
}
