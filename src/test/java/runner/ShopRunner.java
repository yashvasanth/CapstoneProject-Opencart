package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/Shopping.feature",
	    glue = {"stepDefinitions"},
	    plugin = {"pretty", "json:target/cucumber-reports/Shoppingcart.json"}
	)
public class ShopRunner extends AbstractTestNGCucumberTests {
	   
}
