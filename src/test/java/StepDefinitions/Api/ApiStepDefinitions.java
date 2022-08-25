package StepDefinitions.Api;

import Models.Api.Board;
import Models.Api.Card;
import Models.Api.Lists;
import StepDefinitions.ApiUITestsAutomations;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiStepDefinitions {

    ApiUITestsAutomations apiUITestsAutomations;
    String id;
    String listId;
    String cardId;
    HashMap<String, String> actualFields;

    public ApiStepDefinitions(ApiUITestsAutomations apiUITestsAutomations) {

        this.apiUITestsAutomations = apiUITestsAutomations;

    }

    @DataTableType
    public Board createBoard(Map<String, String> data) {

        return Board.createBoard(data);

    }

    @DataTableType
    public Lists createLists(Map<String, String> data) {

        return Lists.createLists(data);

    }

    @DataTableType
    public Card createCard(Map<String, String> data) {

        return Card.createCard(data);

    }

    @When("POST {string} endpoint is called with parameters")
    public void postEndpointIsCalledWithParameters(String endpoint, Map<String, String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint)
                .setMapAsQueryParameters(params)
                .sendPostRequest();

    }

    @When("^PUT (.*) endpoint is called for update card with card id and parameters$")
    public void putEndpointIsCalledWithCardId(String endpoint, Map<String, String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint.replace("{id}", this.cardId.replaceAll("^\"|\"$", "")))
                .setMapAsQueryParameters(params)
                .sendPutRequest();

    }

    @When("^PUT (.*) endpoint is called for update board with board id and parameters$")
    public void putEndpointIsCalledWithBoardId(String endpoint, Map<String, String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint + id)
                .setMapAsQueryParameters(params)
                .sendPutRequest();

    }

    @When("POST {string} endpoint is called with board id and list parameters")
    public void postEndpointIsCalledWithBoardIdAndParameters(String endpoint, Map<String, String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint.replace("{id}", this.id))
                .setMapAsQueryParameters(params)
                .sendPostRequest();

    }

    @When("DELETE {string} endpoint is with board id")
    public void deleteEndpointIsWithBoardId(String endpoint) {

        apiUITestsAutomations.restAssured.setEndpoint(endpoint + id)
                .sendDeleteRequest();

    }

    @And("POST {string} endpoint is called with list id and card parameters")
    public void postEndpointIsCalledWithListIdAndCardParameters(String endpoint, Map<String, String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint)
                .setQueryParameters("idList", this.id)
                .setMapAsQueryParameters(params)
                .sendPostRequest();

    }

    @And("GET {string} from response body")
    public void getBoardIDFromUsingBoardName(String val) {

        this.id = apiUITestsAutomations.restAssured.getResponseSingleField(val);
        System.out.println("id : " + this.id);

    }

    @And("GET {string} endpoint is called with id and parameters")
    public void getEndpointIsCalledIdAndParameters(String endpoint, List<String> params) {

        apiUITestsAutomations.restAssured.setEndpoint(endpoint + this.id)
                .setListAsQueryParameters(params)
                .sendGetRequest();

        actualFields = apiUITestsAutomations.restAssured.getResponseAllFields();

    }

    @And("Updated fields should be")
    public void updatedFieldsShouldBe(List<Board> expectedFields) {

        expectedFields.get(0).setId(id);

        try {

            JSONAssert.assertEquals(apiUITestsAutomations.gsonUtils.toJson(expectedFields.get(0)),
                    apiUITestsAutomations.gsonUtils.toJson(actualFields), true);

        } catch (JSONException e) {

            System.out.println("Json assertion exception threw");
            e.printStackTrace();

        }

    }

    @And("List fields should contains")
    public void listFieldsShouldContains(List<Lists> expectedFields) {


        actualFields = apiUITestsAutomations.restAssured.getResponseAllFields();

        try {

            JSONAssert.assertEquals(apiUITestsAutomations.gsonUtils.toJson(expectedFields.get(0)),
                    apiUITestsAutomations.gsonUtils.toJson(actualFields), false);

        } catch (JSONException e) {

            System.out.println("Json assertion exception threw");
            e.printStackTrace();

        }

    }

    @And("Card fields should contains")
    public void cardFieldsShouldContains(List<Card> expectedFields) {

        actualFields = apiUITestsAutomations.restAssured.getResponseAllFields();

        try {

            JSONAssert.assertEquals(apiUITestsAutomations.gsonUtils.toJson(expectedFields.get(0)),
                    apiUITestsAutomations.gsonUtils.toJson(actualFields), false);

        } catch (JSONException e) {

            System.out.println("Json assertion exception threw");
            e.printStackTrace();

        }

    }

    @And("^GET (.*) endpoint is called for board$")
    public void getStringEndpointIsCalledForAllBoards(String endpoint) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint)
                .setQueryParameters("fields", "name")
                .sendGetRequest();

        id = JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString()).getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("id")
                .toString();

    }

    @And("^GET (.*) endpoint is called$")
    public void checkCardIsCreatedUsingEndpoint(String endpoint) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint.replace("{id}", this.id.replaceAll("^\"|\"$", "")))
                .sendGetRequest();

        cardId = JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString()).getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("id")
                .toString();

    }

    @And("^Check (.*) card is created$")
    public void checkCardIsCreated(String cardName) {

        Assert.assertEquals(cardName, JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("name")
                .toString()
                .replaceAll("^\"|\"$", ""));

    }

    @And("^GET (.*) endpoint is called with board Id$")
    public void getEndpointWithBoardId(String endpoint) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint.replace("{id}", this.id.replaceAll("^\"|\"$", "")))
                .sendGetRequest();

    }

    @And("^GET (.*) list name$")
    public void getNewListListName(String ListName) {

        JsonArray outputJsonArray = JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString()).getAsJsonArray();

        for (int i = 0; i < outputJsonArray.size(); i++) {

            if (outputJsonArray.get(i).getAsJsonObject().get("name").toString().contains(ListName)) {

                listId = outputJsonArray.get(i).getAsJsonObject().get("id").toString();

            }

        }

    }

    @And("^PUT (.*) move card to new list with Id$")
    public void moveCardToNewListWithId(String endpoint) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint.replace("{id}", this.cardId.replaceAll("^\"|\"$", "")))
                .setQueryParameters("idList", this.listId.replaceAll("^\"|\"$", ""))
                .sendPutRequest();

    }
}
