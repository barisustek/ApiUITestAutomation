package Models.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user")
    private WebElement usernameTextBox;

    @FindBy(id = "password")
    private WebElement passwordTextBox;

    @FindBy(id = "login")
    private WebElement loginWithAtlassianButton;

    @FindBy(id = "login-submit")
    private WebElement loginSubmitButton;

    public void setUsernameTextBox(String input){

        setTextBox(input,usernameTextBox);

    }

    public void setPasswordTextBox(String input){

        setTextBox(input, passwordTextBox);

    }

    public void clickLoginWithAtlassianButton(){

        clickWebElement(loginWithAtlassianButton);

    }

    public void clickLoginSubmitButton(){

        clickWebElement(loginSubmitButton);

    }

    public BoardsPage navigateToBoardsPage(){

        clickWebElement(loginSubmitButton);
        return new BoardsPage(webDriver);

    }

}
