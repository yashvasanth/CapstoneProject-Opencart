package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/ProductDetails.feature",
	    glue = {"stepDefinitions"},
	    plugin = {"pretty", "json:target/cucumber-reports/ProductDetails.json"}
	)
public class ProductDetailsRunner extends AbstractTestNGCucumberTests{

}
