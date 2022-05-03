package core.tests.profile;

import core.TestSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static site.pages.HomePage.HomePageElements.LOGIN_BUTTON;
import static site.pages.LoginPage.LoginPageElements.LOGIN_FILED;
import static site.pages.LoginPage.LoginPageElements.PASSWORD_FIELD;
import static site.pages.LoginPage.LoginPageElements.SIGN_IN_BUTTON;
import static site.pages.MainPage.MainPageElements.NAVIGATION_OPTION;
import static site.pages.MainPage.MainPageElements.NAVIGATOR_EXPANDER;
import static site.pages.PagesNaming.HOME_PAGE;
import static site.pages.PagesNaming.LOGIN_PAGE;
import static site.pages.PagesNaming.MAIN_PAGE;
import static steps.ui.StepsUI.CLICK_ON_ELEMENT;
import static steps.ui.StepsUI.CLICK_ON_ELEMENT_WITH_VALUE;
import static steps.ui.StepsUI.ENTER_ENCRYPTED_VALUE_INTO_FIELD;
import static steps.ui.StepsUI.OPEN_PAGE_BY_LINK;
import static steps.ui.StepsUI.PAGE_IS_LOADED;
import static steps.ui.StepsUI.PAGE_IS_OPENED;

public class ProfileIconNavigationTests extends TestSetup {

    @Test(groups = {REGRESSION,PROFILE})
    @Story("TAF-02")
    @Description("Test ability to open Profile page for logged user")
    public void userAbleToOpenProfilePage() {
        OPEN_PAGE_BY_LINK(HOME_PAGE, "baseUrl");
        CLICK_ON_ELEMENT(LOGIN_BUTTON);
        PAGE_IS_OPENED(LOGIN_PAGE);
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(LOGIN_FILED, "gitHubLogin");
        ENTER_ENCRYPTED_VALUE_INTO_FIELD(PASSWORD_FIELD, "gitHubPassword");
        CLICK_ON_ELEMENT(SIGN_IN_BUTTON);
        PAGE_IS_OPENED(MAIN_PAGE);
        PAGE_IS_LOADED();
        CLICK_ON_ELEMENT(NAVIGATOR_EXPANDER);
        CLICK_ON_ELEMENT_WITH_VALUE(NAVIGATION_OPTION, "Your profile");
    }
}
