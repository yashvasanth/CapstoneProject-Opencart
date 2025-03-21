package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OrderPlacementsSteps {
    WebDriver driver;
    HomePage homePage;
    ProductDetailsPage productPage;
    CheckoutPage checkoutPage;

    @Given("I search for a {string}")
    public void i_search_for_a_product(String productName) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com.gr/");
        
        homePage = new HomePage(driver);
        productPage = new ProductDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);

        homePage.searchForProduct(productName);
      
    }

    @And("I open {string} details page")
    public void i_open_the_product_details_page(String product) {
        productPage.openProductPage(product);
    }

    @And("I add it to the cart")
    public void i_add_the_product_to_the_cart() {
        productPage.addToCart();
    }

    @When("I go to checkout")
    public void i_proceed_to_checkout() {
        productPage.moveToCheckout();
    }

    @And("I choose guest checkout")
    public void i_choose_guest_checkout() {
        checkoutPage.selectGuestCheckout();
    }

//    @And("I enter billing details {string}, {string}, {string},{string}, {string}, {string}, {string}, {string}, {string}")
//    public void i_enter_billing_details(String name,String lastName, String email, String phone, String address, String city, String postcode, String country, String region) {
//        checkoutPage.enterBillingDetails(name,lastName, email, phone, address, city, postcode, country, region);
//    }
    

 

    @When("I enter billing details {string},{string},{string},{string},{string}, {string}, {string}, {string}, {string}")
    public void i_enter_billing_details(String name, String lastName, String email, String phone, String address, String city, String postcode, String country, String region) {
    	 checkoutPage.enterBillingDetails(name,lastName, email, phone, address, city, postcode, country, region);
    }





    @And("I continue with shipping")
    public void i_continue_with_the_shipping_method() {
        checkoutPage.continueShippingMethod();
    }

    @And("I agree to the terms")
    public void i_agree_to_the_terms_and_conditions() {
        checkoutPage.agreeToTerms();
    }

    @And("I select the payment method")
    public void i_continue_with_the_payment_method() {
        checkoutPage.continuePaymentMethod();
    }

    @And("I confirm the order")
    public void i_confirm_the_order() {
        checkoutPage.confirmOrder();
    }

    @Then("I should see an order confirmation message")
    public void i_should_see_a_confirmation_message_for_order_placement() {
        Assert.assertTrue(checkoutPage.isOrderPlaced(), "Order placement failed!");
        driver.quit();
    }
}
