package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/OrderPlacement.feature",
		glue = "stepDefinitions",
		plugin = {"pretty", "json:target/cucumber-reports/Order.json"}
				
	)

public class OrderRunner extends AbstractTestNGCucumberTests {

}
