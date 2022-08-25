package Models.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(xpath = "//div[@class='js-list list-wrapper']")
    private List<WebElement> list;

    @FindBy(xpath = "//div[contains(@class, 'js-card-details')]")
    private List<WebElement> cardList ;

    @FindBy(xpath = "//div[contains(@class,'js-desc')]/p")
    private WebElement cardDescription;

    public void clickCreateButton(){

        clickWebElement(createButton);

    }

    public void clickCreateBoardButton(){

        clickWebElement(createBoardButton);

    }

    public void clickCreateBoardSubmitButton(){

        clickWebElement(createBoardSubmitButton);

    }

    public String getCardDescription(){

        waitForElement(cardDescription);
        return cardDescription.getText();

    }

    public void setBoardTitleTextBox(String boardName){

        setTextBox(boardName,boardTitleTextBox);

    }

    public void createNewCard(String listName,String desc){

        for (WebElement element : list) {

            if (element.getText().equals(listName)) {
                setTextBox(desc, element.findElement(By.xpath("//textarea[contains(@class,'js-card-title')]")));
                clickWebElement(element.findElement(By.xpath("//input[contains(@class,'js-add-card')]")));
            }
        }

    }

    public void clickCardInList(String cardDesc,String listName){

        for (WebElement element : list) {

            if (element.findElement(By.xpath("//div/div/div/textarea[contains(@class, 'js-list-name-input')]")).getText().contains(listName)) {

                for(WebElement elementCard : cardList){

                    if (elementCard.findElement(By.xpath("//span[contains(@class,'js-card-name')]")).getText().equals(cardDesc)){

                        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
                        executor.executeScript("arguments[0].click();", elementCard.findElement(By.xpath("//div[contains(@class,'js-card-details')]")));

                    }

                }

            }
        }

    }

}
