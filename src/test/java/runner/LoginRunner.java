package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.DriverFactory;

@CucumberOptions(
	    features = "src/test/resources/features/User.feature",
	    glue = {"stepDefinitions"},
	    plugin = {"pretty", "json:target/cucumber-reports/Login.json"}
	)
public class LoginRunner extends AbstractTestNGCucumberTests {
    @BeforeClass
    public static void setup() {
        DriverFactory.initDriver("chrome");
    }

    @AfterClass
    public static void teardown() {
        DriverFactory.quitDriver();
    
	}
}