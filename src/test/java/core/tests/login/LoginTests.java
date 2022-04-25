package core.tests.login;

import core.tests.TestSetup;
import org.testng.annotations.Test;
import steps.ui.Steps;

import static steps.ui.Steps.CLICK_ON_ELEMENT;
import static steps.ui.Steps.ENTER_VALUE_INTO_FIELD;
import static steps.ui.Steps.OPEN_PAGE_BY_LINK;
import static steps.ui.Steps.PAGE_IS_LOADED;

public class LoginTests extends TestSetup {

    Steps uiSteps = new Steps();

    @Test
    public void loginWithValidCred() throws InterruptedException {
        OPEN_PAGE_BY_LINK("Home page", "baseUrl");
        CLICK_ON_ELEMENT("Login button");
        PAGE_IS_LOADED("Login page");
        ENTER_VALUE_INTO_FIELD("fugemond", "Login field");
        ENTER_VALUE_INTO_FIELD("1488EVREi1488", "Password field");
        CLICK_ON_ELEMENT("Sign in button");
    }

}
