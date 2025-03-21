package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/HomePage.feature",
	    glue = "stepDefinitions",
	    plugin = {"pretty", "json:target/cucumber-reports/HomePage.json"}
	)

public class HomeRunner extends AbstractTestNGCucumberTests {

	
}
