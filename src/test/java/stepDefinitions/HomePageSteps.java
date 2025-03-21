package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.BrowserManager;

public class HomePageSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Given("User is on the Opencart homepage")
    public void user_is_on_homepage() {
        driver = BrowserManager.getDriver();
        driver.get("https://demo.opencart.com.gr/"); 
        homePage = new HomePage(driver);
    }

    @When("User searches for {string}")
    public void user_searches_for(String productName) {
        homePage.searchProduct(productName);
    }
    
    @Then("Search results for {string} should be displayed")
    public void search_results_for_should_be_displayed(String product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'" + product + "')]")));
        Assert.assertTrue(searchResult.isDisplayed(), "Search results for " + product + " are not displayed.");
        BrowserManager.quitDriver();
    }

}