package core.tests.login;

import core.TestSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static site.pages.LoginPage.LoginPageElements.*;
import static site.pages.HomePage.HomePageElements.*;
import static site.pages.PagesNaming.*;
import static steps.ui.AssertionSteps.ASSERT_THAT_CURRENT_URL_IS;
import static steps.ui.AssertionSteps.ASSERT_THAT_ELEMENT_CONTAINS_VALUE;
import static steps.ui.AssertionSteps.VERIFY_ELEMENT_CONDITION;
import static steps.ui.StepsUI.CLICK_ON_ELEMENT;
import static steps.ui.StepsUI.ENTER_ENCRYPTED_VALUE_INTO_FIELD;
import static steps.ui.StepsUI.ENTER_VALUE_INTO_FIELD;
import static steps.ui.StepsUI.OPEN_PAGE_BY_LINK;
import static steps.ui.StepsUI.PAGE_IS_OPENED;

public class LoginWithInValidCredTests extends TestSetup {

    static final String INCORRECT_CRED_ERROR_MESSAGE = "Incorrect username or password.";

    @Test(groups = {REGRESSION, LOGIN})
    @Story("TAF-01")
    @Description("Test login into site using invalid username")
    public void loginWithIncorrectLogin() {
        OPEN_PAGE_BY_LINK(HOME_PAGE, "baseUrl");
        CLICK_ON_ELEMENT(LOGIN_BUTTON);
        PAGE_IS_OPENED(LOGIN_PAGE);
        ENTER_VALUE_INTO_FIELD(LOGIN_FILED, "asdkj123l");
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(PASSWORD_FIELD, "gitHubPassword");
        CLICK_ON_ELEMENT(SIGN_IN_BUTTON);
        VERIFY_ELEMENT_CONDITION(ERROR_BANNER, visible);
        ASSERT_THAT_ELEMENT_CONTAINS_VALUE(ERROR_BANNER, INCORRECT_CRED_ERROR_MESSAGE);
        ASSERT_THAT_CURRENT_URL_IS("https://github.com/session");
    }

    @Test(groups = {REGRESSION, LOGIN})
    @Story("TAF-01")
    @Description("Test login into site using invalid password")
    public void loginWithIncorrectPassword() {
        OPEN_PAGE_BY_LINK(HOME_PAGE, "baseUrl");
        CLICK_ON_ELEMENT(LOGIN_BUTTON);
        PAGE_IS_OPENED(LOGIN_PAGE);
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(LOGIN_FILED, "gitHubLogin");
        ENTER_VALUE_INTO_FIELD(PASSWORD_FIELD, "123sad12");
        CLICK_ON_ELEMENT(SIGN_IN_BUTTON);
        VERIFY_ELEMENT_CONDITION(ERROR_BANNER, visible);
        ASSERT_THAT_ELEMENT_CONTAINS_VALUE(ERROR_BANNER, INCORRECT_CRED_ERROR_MESSAGE);
        ASSERT_THAT_CURRENT_URL_IS("https://github.com/session");
    }

}
