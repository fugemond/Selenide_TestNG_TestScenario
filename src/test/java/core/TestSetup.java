package core;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static core.TestScenario.getTestScenario;

public class TestSetup {

    protected final static String REGRESSION = "regression";
    protected final static String SMOKE = "smoke";
    protected final static String LOGIN = "login";
    protected final static String PROFILE = "profile";
    protected final static String BUG = "bug";

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
        getTestScenario().setEnvironment(new EnvContainer());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
        getTestScenario().removeEnvironment();
    }
}
