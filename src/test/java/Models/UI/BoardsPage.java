package Models.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BoardsPage extends BasePage{

    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-test-id='header-create-menu-button']")
    private WebElement createButton;

    @FindBy(xpath = "//button[@data-test-id='header-create-board-button']")
    private WebElement createBoardButton;

    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    private WebElement boardTitleTextBox;

    @FindBy(xpath = "//button[@data-test-id='create-board-submit-button']")
    private WebElement createBoardSubmitButton;

    @FindBy(xpath = "//h1[contains(@class, 'board-header-btn-text')]")
    private WebElement boardName;

    @FindBy(xpath = "//div[@class='list js-list-content']")
    private List<WebElement> cardList;

    public void clickCreateButton(){

        clickWebElement(createButton);

    }

    public void clickCreateBoardButton(){

        clickWebElement(createBoardButton);

    }

    public void clickCreateBoardSubmitButton(){

        clickWebElement(createBoardSubmitButton);

    }

    public String getBoardName(){

        waitForElement(boardName);
        return boardName.getText();

    }

    public void setBoardTitleTextBox(String boardName){

        setTextBox(boardName,boardTitleTextBox);

    }

    public void createNewCard(String listName,String desc){

        for (WebElement element : cardList) {

            if (element.getText().equals(listName)) {
                setTextBox(desc, element.findElement(By.xpath("//textarea[contains(@class,'js-card-title')]")));
                clickWebElement(element.findElement(By.xpath("//input[contains(@class,'js-add-card')]")));
            }
        }

    }

}
