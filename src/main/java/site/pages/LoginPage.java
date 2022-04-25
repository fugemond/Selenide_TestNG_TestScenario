package site.pages;

import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import core.annotations.Name;
import org.openqa.selenium.support.FindBy;

@Name("Login page")
public class LoginPage extends BasePage {

    @Name("Login field")
    @FindBy(css = "[name='login']")
    SelenideElement loginField;

    @Name("Password field")
    @FindBy(css = "[name='password']")
    SelenideElement passwordField;

    @Name("Sign in button")
    @FindBy(css = "[name='commit']")
    SelenideElement signInButton;
}
