package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    // Locators
    private By checkoutTitle = By.xpath("//h1[contains(text(),'Checkout')]");
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.id("button-login");

    private By checkoutButton = By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a/span");
    private By guestCheckoutRadio = By.xpath("//input[@value='guest']");
    private By continueButton = By.id("button-account");
    private By firstNameField = By.id("input-payment-firstname");
    private By lastNameField = By.id("input-payment-lastname");
    private By emailField = By.id("input-payment-email");
    private By telephoneField = By.id("input-payment-telephone");
    private By addressField = By.id("input-payment-address-1");
    private By cityField = By.id("input-payment-city");
    private By postcodeField = By.id("input-payment-postcode");
    private By countryDropdown = By.id("input-payment-country");
    private By regionDropdown = By.name("zone_id");
    private By continueBillingButton = By.id("button-guest");
    private By shippingMethodContinue = By.id("button-shipping-method");
    private By agreeTermsCheckbox = By.name("agree");
    private By paymentContinueButton = By.id("button-payment-method");
    private By confirmOrderButton = By.id("button-confirm");
    private By successMessage = By.xpath("//h1[contains(text(),'Your order has been placed!')]");
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return waitForElement(checkoutTitle).isDisplayed();
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(loginButton);
    }
   
        public void proceedToCheckout() {
            waitForElementClickable(checkoutButton);
            driver.findElement(checkoutButton).click();
        }

        public void selectGuestCheckout() {
        	waitForElementClickable(guestCheckoutRadio);
            driver.findElement(guestCheckoutRadio).click();
            driver.findElement(continueButton).click();
        }

        public void enterBillingDetails(String firstName, String lastName, String email, String telephone, String address, String city, String postcode, String country, String region)  {
         
    		
        	waitForElementClickable(firstNameField);
    		  
    		  driver.findElement(firstNameField).clear();
    		  driver.findElement(firstNameField).sendKeys(firstName);
    		  
    		  driver.findElement(lastNameField).clear();
    		  driver.findElement(lastNameField).sendKeys(lastName);
    		  
    		  driver.findElement(emailField).clear();
    		  driver.findElement(emailField).sendKeys(email);
    		  
    		  driver.findElement(telephoneField).clear();
    		  driver.findElement(telephoneField).sendKeys(telephone);
    		  
    		  driver.findElement(addressField).clear();
    		  driver.findElement(addressField).sendKeys(address);
    		  
    		  driver.findElement(cityField).clear();
    		  driver.findElement(cityField).sendKeys(city);
    		  
    		  driver.findElement(postcodeField).clear();
    		  driver.findElement(postcodeField).sendKeys(postcode);
    		  
    		  
    		  waitForElementClickable(countryDropdown);
    		  
    		  Select s1= new Select(driver.findElement(countryDropdown));
    		  s1.selectByVisibleText(country);
    		  
    		 
    			/*
    			 * waitForElementToBeVisible(regionDropdown);
    			 * 
    			 * Select s= new Select(driver.findElement(regionDropdown));
    			 * s.selectByVisibleText(region);
    			 */
    		 
    		  waitForElementClickable(regionDropdown);
    		  
    		  driver.findElement(regionDropdown).click();
    		  Select s= new Select(driver.findElement(regionDropdown));
    		  waitForElementVisible(regionDropdown);
    		  s.selectByVisibleText(region);
    		  
    		 
            
    		  waitForElementClickable(continueBillingButton);
            driver.findElement(continueBillingButton).click();
        }

        public void continueShippingMethod() {
        	waitForElementClickable(shippingMethodContinue);
            driver.findElement(shippingMethodContinue).click();
        }

        public void agreeToTerms() {
        	waitForElementClickable(agreeTermsCheckbox);
            driver.findElement(agreeTermsCheckbox).click();
        }

        public void continuePaymentMethod() {
        	waitForElementClickable(paymentContinueButton);
            driver.findElement(paymentContinueButton).click();
        }

        public void confirmOrder() {
        	waitForElementClickable(confirmOrderButton);
            driver.findElement(confirmOrderButton).click();
        }

        public boolean isOrderPlaced() {
            waitForElementVisible(successMessage);
            return driver.findElements(successMessage).size() > 0;
        }

    
    }


