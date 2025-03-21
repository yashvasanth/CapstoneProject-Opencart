package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/search.feature",
	    glue = {"stepDefinitions"},
	    plugin = {
	        "pretty",
	        "json:target/cucumber-reports/SearchFunctionality.json"
	    }
	)
public class SearchFunctionality extends AbstractTestNGCucumberTests {
	    
}