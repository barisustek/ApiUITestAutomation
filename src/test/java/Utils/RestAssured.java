package Utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssured extends ConfigReader {

    private RestAssured apiUtilsInstance = null;

    private final RequestSpecification request;
    private final String url;
    private Response response;
    ConfigReader cr = new ConfigReader();

    public RestAssured() {

        this.url = getApiURL();
        this.request = given().queryParam("key", getApiKeyString()).queryParam("token", getApiTokenString())
                .header("Content-Type", "application/json");

    }

    public RestAssured getInstance() {

        if (apiUtilsInstance == null)
            apiUtilsInstance = new RestAssured();

        return apiUtilsInstance;
    }

    public RequestSpecification getRequestSpecification() {
        return request;
    }

    public void resetRestAssured() {
        apiUtilsInstance = null;
    }

    public RestAssured setQueryParameters(String key, String val) {

        getRequestSpecification().queryParam(key, val);
        return this;

    }

    public RestAssured setMapAsQueryParameters(Map<String, String> keyVal) {

        getRequestSpecification().queryParams(keyVal);
        return this;

    }

    public RestAssured setListAsQueryParameters(List<String> keyVal) {

        getRequestSpecification().queryParams("fields", keyVal);
        return this;

    }

    public RestAssured setEndpoint(String endpoint) {

        getRequestSpecification().baseUri(this.url + endpoint);
        return this;

    }

    public void sendGetRequest() {

        this.response = getRequestSpecification().when().get();
    }

    public void sendPutRequest() {

        this.response = getRequestSpecification().when().put();

    }

    public void sendDeleteRequest() {

        this.response = getRequestSpecification().when().delete();

    }

    public void sendPostRequest() {

        this.response = getRequestSpecification().when().post();

    }

    public int getResponseStatusCode() {

        return response.statusCode();

    }

    public String getResponseSingleField(String path) {

        return response.jsonPath().get(path);

    }

    public LinkedHashMap<String, String> getResponseAllFields() {

        return response.jsonPath().get();

    }

    public String getResponseBodyAsString(){

        return response.asString();

    }

}