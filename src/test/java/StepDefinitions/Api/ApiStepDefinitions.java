package StepDefinitions.Api;

import Models.Api.Board;
import StepDefinitions.ApiUITestsAutomations;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.*;

public class ApiStepDefinitions {

    ApiUITestsAutomations apiUITestsAutomations;
    String boardId;
    HashMap<String,String> actualFields ;

    public ApiStepDefinitions(ApiUITestsAutomations apiUITestsAutomations) {

        this.apiUITestsAutomations = apiUITestsAutomations;

    }

    @DataTableType
    public Board createBoard(Map<String,String> data){

       return Board.createBoard(data);

    }

    @When("POST {string} endpoint is called with parameters")
    public void postEndpointIsCalledWithParameters(String endpoint, Map<String,String> name) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint)
                                         .setMapAsQueryParameters(name)
                                         .sendPostRequest();

    }

    @Then("Http status code should be {int} and {string} should return in response body")
    public void httpStatusCodeShouldBeAndShouldReturnInResponseBody(int statusCode, String boardName) {

        Assert.assertEquals(statusCode,apiUITestsAutomations.restAssured.getResponseStatusCode());
        Assert.assertEquals(boardName, apiUITestsAutomations.restAssured.getResponseSingleField("name"));

    }

    @When("PUT {string} endpoint is called with board id and parameters")
    public void putEndpointIsCalledWithBoardId(String endpoint, Map<String,String> params) {

        apiUITestsAutomations.restAssured.resetRestAssured();
        apiUITestsAutomations.restAssured = apiUITestsAutomations.restAssured.getInstance();

        apiUITestsAutomations.restAssured.setEndpoint(endpoint + boardId)
                                         .setMapAsQueryParameters(params)
                                         .sendPutRequest();

    }

    @When("DELETE {string} endpoint is with board id")
    public void deleteEndpointIsWithBoardId(String endpoint) {

        apiUITestsAutomations.restAssured.setEndpoint(endpoint +  boardId)
                .sendDeleteRequest();

    }

    @And("GET {string} from response body")
    public void getBoardIDFromUsingBoardName(String val) {

        this.boardId = apiUITestsAutomations.restAssured.getResponseSingleField(val);

    }

    @Then("Http status code should be {int}")
    public void httpStatusCodeShouldBe(int statusCode) {

        Assert.assertEquals(statusCode,apiUITestsAutomations.restAssured.getResponseStatusCode());

    }

    @And("Updated fields should be")
    public void updatedFieldsShouldBe(List<Board> expectedFields) {

        expectedFields.get(0).setId(boardId);

        try {

            JSONAssert.assertEquals(apiUITestsAutomations.gsonUtils.toJson(expectedFields.get(0)),
                                    apiUITestsAutomations.gsonUtils.toJson(actualFields),true);

        } catch (JSONException e) {

            System.out.println("Json assertion exception threw");
            e.printStackTrace();

        }

    }

    @And("GET {string} endpoint is called id and parameters")
    public void getEndpointIsCalledIdAndParameters(String endpoint, List<String> params) {

        apiUITestsAutomations.restAssured.setEndpoint(endpoint + boardId)
                                         .setListAsQueryParameters(params)
                                         .sendGetRequest();

        actualFields = apiUITestsAutomations.restAssured.getResponseAllFields();

    }
}
