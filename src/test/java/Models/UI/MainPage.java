package Models.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginButton;

    public void clickLogInButton(){

        clickWebElement(loginButton);

    }

    public LoginPage NavigateToLoginPage(){
        clickLogInButton();
        return new LoginPage(webDriver);
    }

}
