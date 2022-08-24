package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class SeleniumUtils extends ConfigReader {

    public WebDriver webDriver;
    public WebDriverWait wait;

    public void runWebDriver() {
        SelectBrowserType();
    }

    public void stopWebDriver() {

        webDriver.quit();

    }

    public void openWebSite() {

        webDriver.get(getApiURL());

    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public String getUrl() {

        return webDriver.getCurrentUrl();

    }

    public void clickWebElement(WebElement element) {

        new WebDriverWait(getWebDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
        element.click();

    }

    public void setTextBox(String input, WebElement element) {

        new WebDriverWait(getWebDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.click();
        element.sendKeys(input);

    }

    public void waitForElement(WebElement element) {

        new WebDriverWait(getWebDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));

    }

    private void SelectBrowserType() {

        switch (getBrowserType().toLowerCase(Locale.ROOT)) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized")
                        .addArguments("--disable-notifications");
                webDriver = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
        }

    }
}