package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.RegisterPage;
import pages.LoginPage;
import pages.ForgotPasswordPage;
import utils.DriverFactory;
import io.cucumber.java.en.*;

public class UserSteps {

    WebDriver driver = DriverFactory.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        driver.get("https://demo.opencart.com.gr/index.php?route=account/register");
    }

    @When("I enter valid registration details")
    public void i_enter_valid_registration_details() {
        registerPage.enterRegistrationDetails("luci", "g", "luci@example.com", "1234567891", "Test@123");
    }

    @And("I accept the privacy policy")
    public void i_accept_the_privacy_policy() {
        registerPage.acceptPrivacyPolicy();
    }

    @And("I click on continue button")
    public void i_click_on_continue_button() {
        registerPage.clickContinue();
    }

    @Then("I should see that my account has been created")
    public void i_should_see_that_my_account_has_been_created() {
	        if (registerPage.isRegistrationSuccessful()) {
	            System.out.println("Registration successful!");
	            Assert.assertTrue(true);
	        } else if (registerPage.isAccountAlreadyExists()) {
	            System.out.println("User already registered. Skipping registration.");
	        } else {
	            Assert.fail("User registration failed!");
	        }
	    
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://demo.opencart.com.gr/index.php?route=account/login");
    }

    @When("I enter valid email and password")
    public void i_enter_valid_email_and_password() {
        loginPage.enterLoginCredentials("vasanth@example.com", "Test@123");
    }

    @And("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(loginPage.isLoginSuccessful());
        loginPage.clickLogout();
    }
    

    @Given("I am on the forgotten password page")
    public void i_am_on_the_forgotten_password_page() {
        driver.get("https://demo.opencart.com.gr/index.php?route=account/forgotten");
    }

    @When("I enter my email address")
    public void i_enter_my_email_address() {
        forgotPasswordPage.enterEmail("vasanth@example.com");
    }

    @And("I click on continue button for password recovery")
    public void i_click_on_continue_button_for_password_recovery() {
        forgotPasswordPage.clickContinue();
    }

    @Then("I should see a confirmation message that an email has been sent")
    public void i_should_see_a_confirmation_message_that_an_email_has_been_sent() {
        Assert.assertTrue(forgotPasswordPage.isConfirmationMessageDisplayed());
        driver.quit();
    }
}
