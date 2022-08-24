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

    @Given("User lands on trello home page")
    public void userLandsOnTrelloHomePage() {

        mainPage = new MainPage(this.apiUITestsAutomations.seleniumUtils.getWebDriver());
        this.apiUITestsAutomations.seleniumUtils.openWebSite();

    }

    @When("User click log in")
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

    @And("User click login with atlassian button")
    public void userClickLoginWithAtlassianButton() {

        loginPage.clickLoginWithAtlassianButton();

    }

    @And("User click login button")
    public void userClickLoginSubmitButton() {

        boardsPage = loginPage.navigateToBoardsPage();

    }

    @And("User click create button")
    public void userClickCreateButton() {

        boardsPage.clickCreateButton();

    }

    @And("User click create board button")
    public void userClickCreateBoardButton() {

        boardsPage.clickCreateBoardButton();

    }

    @And("^User set (.*) board title$")
    public void userSetBoardTitleBoardTitle(String boardName) {

        boardsPage.setBoardTitleTextBox(boardName);

    }

    @And("User click create submit board button")
    public void userClickCreateSubmitBoardButton() {

        boardsPage.clickCreateBoardSubmitButton();

    }

    @Then("^(.*) should create$")
    public void boardtitleShouldCreate(String boardName) {

        Assert.assertEquals(boardName, boardsPage.getBoardName());
        Assert.assertEquals(boardName.toLowerCase(Locale.ROOT),
                apiUITestsAutomations.seleniumUtils.getUrl().substring(apiUITestsAutomations.seleniumUtils
                        .getUrl()
                        .lastIndexOf('/') + 1));

    }

    @And("^User create card to (.*) list with (.*) card description$")
    public void userCreateCardToListListWithDescriptionDescription(String listName, String description) {

        boardsPage.createNewCard(listName, description);

    }

}
