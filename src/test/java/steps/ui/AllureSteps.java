package steps.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static core.TestScenario.getTestScenario;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static utility.Spectators.waitForPageLoad;

@Log4j2
public class AllureSteps {

    @Step("Open page {0}, by the link {1}")
    static void openPageByLink(String pageToOpen, String urlToOpen) {
        log.info("Open page: \"{}\" by URL: \"{}\"", pageToOpen, urlToOpen);
        getWebDriver().navigate().to(urlToOpen);
        log.info("\"{}\" - Page is opened", pageToOpen);
        getTestScenario().setCurrentPage(getTestScenario().getPage(pageToOpen));
    }

    @Step("Click on element: {0}")
    static void clickOnElement(String elementToClick) {
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementToClick);
        log.info("Click on button: \"{}\"", elementToClick);
        element.shouldHave(Condition.visible).click();
    }

    @Step("Click on element: {0}, which has value: {1}")
    static void clickOnElementWithValue(String elementToClick, String value) {
        SelenideElement element = getTestScenario().getCurrentPage().getElementWithParam(elementToClick, value);
        log.info("Click on button: \"{}\"", elementToClick);
        element.shouldHave(Condition.visible).click();
    }

    @Step("Into the field: {0}, enter the value: {1}")
    static void enterValueIntoField(String elementToEnterValue, String value) {
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementToEnterValue);
        log.info("Enter \"{}\" into the field: \"{}\" ", value, elementToEnterValue);
        element.sendKeys(value);
    }

    @Step("Into the field: {0}, enter encrypted value: {1}")
    static void enterEncryptedValueIntoField(String elementToEnterValue, String inputText) {
        SelenideElement element = getTestScenario().getCurrentPage().getElement(elementToEnterValue);
        log.info("Enter encrypted value into the field: \"{}\" ", elementToEnterValue);
        element.sendKeys(inputText);
    }

    @Step("Page: {0} - is opened")
    static void pageIsOpened(String pageName) {
        log.info("\"{}\" - Page is opened", pageName);
        getTestScenario().setCurrentPage(getTestScenario().getPage(pageName));
    }

    @Step("Page is loaded")
    static void pageIsLoaded() {
        log.info("Page is loaded");
        waitForPageLoad();
    }

    @Step("Verify that element: {0}, should be: {1}")
    public static void verifyElementCondition(String pageElement, Condition condition) {
        SelenideElement element = getTestScenario().getCurrentPage().getElement(pageElement);
        log.info("Verify that element: \"{}\" is \"{}\" ", pageElement, condition.getName());
        element.shouldBe(condition);
    }

    @Step("Element: {0}, should contains value: {1}")
    public static void assertThatElementContainsValue(String pageElement, String text) {
        SelenideElement element = getTestScenario().getCurrentPage().getElement(pageElement);
        log.info("Verify that element \"{}\" contains value: \"{}\" is \"{}\" ",pageElement, element.getText(), text);
        assertThat("Element doesn't contain value", element.getText(), containsString(text));
    }

    @Step("Current url should be : {0}")
    public static void assertThatCurrentUrlIs(String url) {
        String currentUrl = getWebDriver().getCurrentUrl();
        log.info("Verify that current url :\"{}\" is equal to: \"{}\"", currentUrl, url);
        assertThat("Current URL is not equal to expected", currentUrl, equalTo(url));
    }
}
