package StepDefinitions;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CommonStepDefinitions {

    ApiUITestsAutomations apiUITestsAutomations ;

    public CommonStepDefinitions(ApiUITestsAutomations apiUITestsAutomations){

        this.apiUITestsAutomations = apiUITestsAutomations;

    }

    @Before
    public void before(){

        apiUITestsAutomations.restAssured.setEndpoint("/1/members/me/boards")
                .setQueryParameters("fields","id")
                .sendGetRequest();

        JsonArray outputJsonArray = JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString()).getAsJsonArray();

        for(int i =0 ; i<outputJsonArray.size();i++){

            apiUITestsAutomations.restAssured
                    .setEndpoint("/1/boards/" + outputJsonArray.get(i).getAsJsonObject().get("id").toString().replaceAll("^\"|\"$", ""))
                    .sendDeleteRequest();

        }

    }

    @After
    public void after(){

        apiUITestsAutomations.restAssured.setEndpoint("/1/members/me/boards")
                .setQueryParameters("fields","id")
                .sendGetRequest();

        JsonArray outputJsonArray = JsonParser.parseString(apiUITestsAutomations.restAssured.getResponseBodyAsString()).getAsJsonArray();

        for(int i =0 ; i<outputJsonArray.size();i++){

            apiUITestsAutomations.restAssured
                    .setEndpoint("/1/boards/" + outputJsonArray.get(i).getAsJsonObject().get("id").toString().replaceAll("^\"|\"$", ""))
                    .sendDeleteRequest();

        }

    }

    @Then("Http status code should be {int} and {string} should return in response body")
    public void httpStatusCodeShouldBeAndShouldReturnInResponseBody(int statusCode, String boardName) {

        Assert.assertEquals(statusCode,apiUITestsAutomations.restAssured.getResponseStatusCode());
        Assert.assertEquals(boardName, apiUITestsAutomations.restAssured.getResponseSingleField("name"));

    }

    @Then("Http status code should be {int}")
    public void httpStatusCodeShouldBe(int statusCode) {

        Assert.assertEquals(statusCode,apiUITestsAutomations.restAssured.getResponseStatusCode());

    }

}
