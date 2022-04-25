package site.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import core.annotations.Name;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Name("Home page")
public class HomePage extends BasePage {

    @Name("Login button")
    @FindBy(css = "[href*='login']")
    private SelenideElement loginButton;

    @Name("Login buttons")
    @FindBy(css = "[href*='login']")
    private List<SelenideElement> loginButtons;

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }
}
