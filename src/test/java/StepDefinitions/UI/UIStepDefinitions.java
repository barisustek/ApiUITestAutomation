package StepDefinitions.UI;

import Models.UI.BoardsPage;
import Models.UI.LoginPage;
import Models.UI.MainPage;
import StepDefinitions.ApiUITestsAutomations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Locale;

public class UIStepDefinitions {

    ApiUITestsAutomations apiUITestsAutomations;

    public UIStepDefinitions(ApiUITestsAutomations apiUITestsAutomations) {

        this.apiUITestsAutomations = apiUITestsAutomations;

    }

    MainPage mainPage;
    LoginPage loginPage;
    BoardsPage boardsPage;
    String boardName;

    @Given("User opens trello home page")
    public void userLandsOnTrelloHomePage() {

        apiUITestsAutomations.seleniumUtils.runWebDriver();
        mainPage = new MainPage(this.apiUITestsAutomations.seleniumUtils.getWebDriver());
        this.apiUITestsAutomations.seleniumUtils.openWebSite();

    }

    @When("User clicks to log in")
    public void userClickLoginButton() {

        loginPage = mainPage.NavigateToLoginPage();

    }

    @Then("{string} should be displayed")
    public void shouldBeDisplayed(String url) {

        Assert.assertEquals(url, apiUITestsAutomations.seleniumUtils.getUrl());

    }

    @And("^User enters (.*) username$")
    public void userEntersUsername(String username) {

        loginPage.setUsernameTextBox(username);

    }

    @And("^User enters (.*) password$")
    public void userEntersPassword(String password) {

        loginPage.setPasswordTextBox(password);

    }

    @And("User clicks to login with Atlassian button")
    public void userClickLoginWithAtlassianButton() {

        loginPage.clickLoginWithAtlassianButton();

    }

    @And("User clicks to login button")
    public void userClickLoginSubmitButton() {

        boardsPage = loginPage.navigateToBoardsPage();

    }

    @And("User clicks to create button")
    public void userClickCreateButton() {

        boardsPage.clickCreateButton();

    }

    @And("User clicks to create board button")
    public void userClickCreateBoardButton() {

        boardsPage.clickCreateBoardButton();

    }

    @And("^User sets (.*) to board title$")
    public void userSetBoardTitleBoardTitle(String boardName) {

        this.boardName = boardName;
        boardsPage.setBoardTitleTextBox(boardName);

    }

    @And("User clicks to create submit board button")
    public void userClickCreateSubmitBoardButton() {

        boardsPage.clickCreateBoardSubmitButton();
        apiUITestsAutomations.seleniumUtils.waitPageLoad(boardName);

    }

    @Then("^(.*) should be created$")
    public void boardtitleShouldCreate(String boardName) {

        Assert.assertEquals(boardName.toLowerCase(Locale.ROOT),
                apiUITestsAutomations.seleniumUtils.getUrl().substring(apiUITestsAutomations.seleniumUtils
                        .getUrl()
                        .lastIndexOf('/') + 1));

    }

    @And("^User creates card to (.*) list with (.*) card description$")
    public void userCreateCardToListListWithDescriptionDescription(String listName, String description) {

        boardsPage.createNewCard(listName, description);

    }

    @And("^User clicks (.*) in (.*)$")
    public void userClickCardNameInList(String cardName, String list) {

        boardsPage.clickCardInList(cardName, list);

    }

    @Then("^Card description should equal to (.*)$")
    public void cardDescriptionShouldEqualsDescription(String description) {

        Assert.assertEquals(boardsPage.getCardDescription(), description);

    }

    @Then("Check card in <NewList>")
    public void checkCardInNewList(String newList) {


    }
}
