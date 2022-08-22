package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CommonStepDefinitions {

    ApiUITestsAutomations apiUITestsAutomations ;

    public CommonStepDefinitions(ApiUITestsAutomations apiUITestsAutomations){

        this.apiUITestsAutomations = apiUITestsAutomations;

    }

    @Before
    public void before(){

        //TODO : Methods will be written to delete previously created data before test runs.

    }

    @After
    public void after(){

        //TODO : Same as before() method but runs after tests.

    }

}
