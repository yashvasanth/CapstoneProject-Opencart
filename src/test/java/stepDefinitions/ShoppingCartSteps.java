package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import utils.WebDriverManager;

public class ShoppingCartSteps {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

   

    @Given("User is on the OpenCart homepage")
    public void user_is_on_the_opencart_homepage() {
    	 driver = WebDriverManager.getDriver("chrome");
    	 homePage = new HomePage(driver);
         productPage = new ProductPage(driver);
         cartPage = new CartPage(driver);
         checkoutPage = new CheckoutPage(driver);
         driver.manage().deleteAllCookies();
         
         homePage.navigateToHomePage("https://demo.opencart.com.gr/");
         
         
         
        
    }

    @When("User navigates to {string} category")
    public void user_navigates_to_category(String category) {
        homePage.navigateToCategory(category);
    }

    @When("User searches for {string} in the search bar")
    public void user_searches_for_in_the_search_bar(String searchTerm) {
        homePage.searchForProduct1(searchTerm);
    }

    @When("User adds {string} to cart")
    public void user_adds_to_cart(String product) {
        if (homePage.isProductDisplayedInSearchResults(product)) {
            homePage.clickOnProductInSearchResults(product);
        } else {
            homePage.clickOnProduct(product);
        }
        productPage.addToCart();
    }

    @When("User navigates to shopping cart")
    public void user_navigates_to_shopping_cart() {
        homePage.navigateToCart();
    }

    @Then("User should see {int} products in the cart")
    public void user_should_see_products_in_the_cart(Integer count) {
        Assert.assertEquals(cartPage.getProductCount(), count.intValue());
    }

    @When("User removes {string} from the cart")
    public void user_removes_from_the_cart(String product) {
        cartPage.removeProduct(product);
    }

    @Then("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
        WebDriverManager.quitDriver();
    }
}