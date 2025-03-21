package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductDetailsPage;

public class ProductDetailsSteps {


	WebDriver driver ;
    ProductDetailsPage productDetailsPage;
    

	HomePage homePage;
	@Given("I open a product page")
	public void i_open_a_product_page() {
		
		driver = new ChromeDriver();
		productDetailsPage = new ProductDetailsPage(driver);
        homePage = new HomePage(driver);
        driver.get("https://demo.opencart.com.gr/");
		homePage.searchForProduct("MacBook Pro");
		
		productDetailsPage.openProductPage("MacBook Pro");

	}
	@Then("I should see the product name")
	public void i_should_see_the_product_name() {
		 Assert.assertFalse(productDetailsPage.getProductTitle().isEmpty(), "Product title is missing!");
	}
	@Then("I should see the product details")
	public void i_should_see_the_product_details() {
		Assert.assertFalse(productDetailsPage.getProductDescription().isEmpty(), "Product description is missing!");

	}
	@Then("I should see the product price")
	public void i_should_see_the_product_price() {
		Assert.assertFalse(productDetailsPage.getProductPrice().isEmpty(), "Product price is missing!");

	}
	@Then("I should see the product image")
	public void i_should_see_the_product_image() {
		 Assert.assertTrue(productDetailsPage.isProductImageDisplayed(), "Product image is missing!");

	}
	@Then("I should see if the product is in stock")
	public void i_should_see_if_the_product_is_in_stock() {
		Assert.assertTrue(productDetailsPage.isProductAvailable(), "Product is out of stock!");
		driver.quit();

	}

	@When("I click Add to Wishlist")
	public void i_click_add_to_wishlist() {
		 productDetailsPage.addToWishlist();

	}
	@Then("I should see a success message of wishlist")
	public void i_should_see_a_success_message_of_wishlist() {
		 Assert.assertTrue(productDetailsPage.isAddedToWishlist(), "Product was not added to wishlist!");
	        driver.quit();

	}

	
	@When("I click Add to Cart")
	public void i_click_add_to_cart() {
		productDetailsPage.addToCart();

	}
	@Then("I should see a success message of cart")
	public void i_should_see_a_success_message_of_cart() {
        Assert.assertTrue(productDetailsPage.isAddedToCart(), "Product was not added to cart!");
        driver.quit();

	}

}
