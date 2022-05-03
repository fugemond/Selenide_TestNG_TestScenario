package steps.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.interfaces.PageElement;
import lombok.extern.log4j.Log4j2;
import site.pages.PagesNaming;

import static core.TestScenario.getTestScenario;
import static steps.ui.AllureSteps.*;
import static utility.DecoderLocal.decodeValue;
import static utility.PropertiesLoader.tryGetPropertyOrDefault;
import static utility.Spectators.waitForPageLoad;

@Log4j2
public class StepsUI {

    public static void OPEN_PAGE_BY_LINK(PagesNaming pageName, String url) {
        String urlToOpen = tryGetPropertyOrDefault(url);
        String pageToOpen = pageName.getPageName();
        openPageByLink(pageToOpen, urlToOpen);
    }

    public static void CLICK_ON_ELEMENT(PageElement pageElement) {
        clickOnElement(pageElement.getElementName());
    }

    public static void CLICK_ON_ELEMENT_WITH_VALUE(PageElement pageElement, String value) {
        clickOnElementWithValue(pageElement.getElementName(), value);
    }

    public static void ENTER_VALUE_INTO_FIELD(PageElement pageElement, String value) {
        String inputText = tryGetPropertyOrDefault(value);
        enterValueIntoField(pageElement.getElementName(), inputText);
    }

    public static void ENTER_ENCRYPTED_VALUE_INTO_FIELD(PageElement pageElement, String inputText) {
        String decryptedValue = decodeValue(tryGetPropertyOrDefault(inputText));
        enterEncryptedValueIntoField(pageElement.getElementName(), decryptedValue);
    }

    public static void PAGE_IS_OPENED(PagesNaming pageName) {
        pageIsOpened(pageName.getPageName());
    }

    public static void PAGE_IS_LOADED() {
        pageIsLoaded();
    }


}
