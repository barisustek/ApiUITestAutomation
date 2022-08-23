package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "StepDefinitions",
        features = "src/test/resources/features",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class ApiUITestAutomationRunner {

}
