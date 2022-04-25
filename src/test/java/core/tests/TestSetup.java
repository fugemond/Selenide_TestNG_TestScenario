package core.tests;

import com.codeborne.selenide.WebDriverRunner;
import core.EnvContainer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static core.TestScenario.getTestScenario;

public class TestSetup {

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
        getTestScenario().setEnvironment(new EnvContainer());
    }

    @AfterTest
    public void tearDown(){
        WebDriverRunner.getWebDriver().quit();
    }
}
