package utility;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.Integer.parseInt;
import static java.time.Duration.*;
import static utility.PropertiesLoader.tryGetPropertyOrDefault;

@UtilityClass
public final class Spectators {

    public static final int DEFAULT_TIMEOUT = parseInt(tryGetPropertyOrDefault("waitingForElementTimeout"));

    public static final ThreadLocal<WebDriverWait> WEB_DRIVER_WAIT = new ThreadLocal<>();

    public static WebDriverWait getWebDriverWait() {
        if (WEB_DRIVER_WAIT.get() == null) {
            setWebDriverWait();
        }
        return WEB_DRIVER_WAIT.get();
    }

    public static void setWebDriverWait() {
        WEB_DRIVER_WAIT.set(new WebDriverWait(getWebDriver(), ofMillis(DEFAULT_TIMEOUT), ofMillis(200)));
    }

    public static void waitForPageLoad() {
        Function<WebDriver, Boolean> loadCondition =
                x -> ((JavascriptExecutor) getWebDriver()).executeScript("return document.readyState").equals(
                        "complete");
        getWebDriverWait().until(loadCondition);
    }
}
