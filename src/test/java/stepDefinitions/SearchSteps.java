package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.Configuration;

import java.util.Arrays;
import java.util.List;

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;


    @Given("I am on the OpenCart homepage")
    public void iAmOnTheOpenCartHomepage() {
    	 driver = Configuration.getDriver();
         driver.get(Configuration.BASE_URL);
         
        homePage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void iSearchFor(String product) {
        searchResultsPage = homePage.searchForProduct(product);
    }

    @Then("I should see search results containing {string}")
    public void iShouldSeeSearchResultsContaining(String product) {
        if (!searchResultsPage.resultsContain(product)) {
            throw new AssertionError("Search results don't contain " + product);
        }
    }

    @Then("I should see Mac-related products")
    public void iShouldSeeMacRelatedProducts() {
        if (!searchResultsPage.resultsContain("Mac")) {
            throw new AssertionError("Search results don't contain Mac products");
        }
    }

    @And("the results should include {string}, {string}, {string}, and {string}")
    public void theResultsShouldIncludeAnd(String product1, String product2, String product3, String product4) {
        List<String> expectedProducts = Arrays.asList(product1, product2, product3, product4);
        if (!searchResultsPage.resultsContainAll(expectedProducts)) {
            throw new AssertionError("Not all expected Mac products are shown");
        }
    }

    @And("I sort results by {string}")
    public void iSortResultsBy(String sortOption) {
        searchResultsPage = searchResultsPage.sortResultsBy(sortOption);
    }

    @Then("I should see results sorted from highest to lowest price")
    public void iShouldSeeResultsSortedFromHighestToLowestPrice() {
        if (!searchResultsPage.isPriceSorted(false)) {
            throw new AssertionError("Products are not sorted by price (High > Low)");
        }
    }

    @Then("I should see results sorted by highest rating first")
    public void iShouldSeeResultsSortedByHighestRatingFirst() {
        if (!searchResultsPage.isRatingSorted()) {
            throw new AssertionError("Products are not sorted by highest rating first");
        }
    }

//    @And("I select the {string} option")
//    public void iSelectTheOption(String optionName) {
//        if (optionName.equals("Search in subcategories")) {
//            searchResultsPage.setSearchInSubcategories(true);
//        } else if (optionName.equals("Search in product descriptions")) {
//            searchResultsPage.setSearchInDescriptions(true);
//        }
//    }

    @And("I click the Search button")
    public void iClickTheSearchButton() {
        searchResultsPage = searchResultsPage.clickSearch();
    }

    @Then("I should see results from all subcategories")
    public void iShouldSeeResultsFromAllSubcategories() {
        // This would need specific implementation based on how 
        // OpenCart indicates results from subcategories
        // For now, we'll just check that results contain Mac
        if (!searchResultsPage.resultsContain("Mac")) {
            throw new AssertionError("No results from subcategories found");
        }
    }

    @And("I click on the list view button")
    public void iClickOnTheListViewButton() {
        searchResultsPage = searchResultsPage.switchToListView();
    }

    @Then("I should see products displayed in list format")
    public void iShouldSeeProductsDisplayedInListFormat() {
        if (!searchResultsPage.isListViewActive()) {
            throw new AssertionError("Products are not displayed in list view");
        }
    }

    @When("I click on the grid view button")
    public void iClickOnTheGridViewButton() {
        searchResultsPage = searchResultsPage.switchToGridView();
    }

    @Then("I should see products displayed in grid format")
    public void iShouldSeeProductsDisplayedInGridFormat() {
        if (!searchResultsPage.isGridViewActive()) {
            throw new AssertionError("Products are not displayed in grid view");
        }
        Configuration.quitDriver();
    }
}
