package core.tests.login;

import core.TestSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static site.pages.HomePage.HomePageElements.LOGIN_BUTTON;
import static site.pages.LoginPage.LoginPageElements.LOGIN_FILED;
import static site.pages.LoginPage.LoginPageElements.PASSWORD_FIELD;
import static site.pages.LoginPage.LoginPageElements.SIGN_IN_BUTTON;
import static site.pages.MainPage.MainPageElements.NAVIGATOR_EXPANDER;
import static site.pages.PagesNaming.HOME_PAGE;
import static site.pages.PagesNaming.LOGIN_PAGE;
import static site.pages.PagesNaming.MAIN_PAGE;
import static steps.ui.AssertionSteps.ASSERT_THAT_CURRENT_URL_IS;
import static steps.ui.AssertionSteps.VERIFY_ELEMENT_CONDITION;
import static steps.ui.StepsUI.CLICK_ON_ELEMENT;
import static steps.ui.StepsUI.ENTER_ENCRYPTED_VALUE_INTO_FIELD;
import static steps.ui.StepsUI.OPEN_PAGE_BY_LINK;
import static steps.ui.StepsUI.PAGE_IS_LOADED;
import static steps.ui.StepsUI.PAGE_IS_OPENED;

public class LoginWithValidCredTests extends TestSetup {

    @Test(groups = {"regression","login","web"}, priority = 2)
    @Story("TAF-01")
    @Description("Test login into site using valid credentials")
    public void loginWithValidCred() {
        OPEN_PAGE_BY_LINK(HOME_PAGE, "baseUrl");
        CLICK_ON_ELEMENT(LOGIN_BUTTON);
        PAGE_IS_OPENED(LOGIN_PAGE);
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(LOGIN_FILED, "gitHubLogin");
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(PASSWORD_FIELD, "gitHubPassword");
        CLICK_ON_ELEMENT(SIGN_IN_BUTTON);
        PAGE_IS_OPENED(MAIN_PAGE);
        PAGE_IS_LOADED();
        VERIFY_ELEMENT_CONDITION(NAVIGATOR_EXPANDER, visible);
        ASSERT_THAT_CURRENT_URL_IS("https://github.com/");
    }
}