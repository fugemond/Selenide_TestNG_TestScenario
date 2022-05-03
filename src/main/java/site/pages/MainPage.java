package site.pages;

import com.codeborne.selenide.SelenideElement;
import core.BasePage;
import core.annotations.Name;
import core.interfaces.PageElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Name("Main page")
public class MainPage extends BasePage {

    @Getter
    @ToString
    @AllArgsConstructor
    public enum MainPageElements implements PageElement {
        NAVIGATOR_EXPANDER("Navigator expander"),
        NAVIGATION_OPTION("Navigation option");

        final String elementName;
    }

    @Name("Navigator expander")
    @FindBy(css = "[aria-label='View profile and more']")
    SelenideElement navigatorExpander;

    @Name("Navigation option")
    @FindBy(xpath = "//a[contains(text(),'%s')]")
    SelenideElement navigationOption;
}
