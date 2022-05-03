package site.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import core.annotations.Name;
import core.interfaces.PageElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Name("Home page")
public class HomePage extends BasePage {

    @Getter
    @ToString
    @AllArgsConstructor
    public enum HomePageElements implements PageElement {
        LOGIN_BUTTON("Login button");

        final String elementName;
    }

    @Name("Login button")
    @FindBy(css = "[href*='login']")
    private SelenideElement loginButton;
}
