package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

		
	    // Constructor
	    public ProductDetailsPage(WebDriver driver) {
	        super(driver);
	    }

	    // Locators
	    
	    By productTitle = By.cssSelector("h1");
	    By productDescription = By.cssSelector(".tab-content");
	    By productPrice = By.xpath("//ul[@class='list-unstyled']//h2");
	    By productImage = By.cssSelector(".thumbnails img");
	    By availabilityStatus = By.xpath("//ul[@class='list-unstyled']//li[contains(text(),'Availability')]");
	    By addToWishlistButton = By.cssSelector("button[data-original-title='Add to Wish List']");
	    By addToCartButton = By.id("button-cart");
	    By cartSuccessMessage = By.xpath("//*[@id=\"product-product\"]/div[1]");
	    By wishlistSuccessMessage = By.xpath("//*[@id=\"product-product\"]/div[1]");
	    By cartButton = By.xpath("//*[@id=\"cart\"]/button");
	    By checkoutLink = By.linkText("Checkout");

	    // Actions
	    
	    public void openProductPage(String productlink) {
	    	driver.findElement(By.linkText(productlink)).click();
	    }
	    public String getProductTitle() {
	        return driver.findElement(productTitle).getText();
	    }

	    public String getProductDescription() {
	        return driver.findElement(productDescription).getText();
	    }

	    public String getProductPrice() {
	        return driver.findElement(productPrice).getText();
	    }

	    public boolean isProductImageDisplayed() {
	        return driver.findElement(productImage).isDisplayed();
	    }

	    public boolean isProductAvailable() {
	        return driver.findElement(availabilityStatus).getText().contains("In Stock");
	    }

	    public void addToWishlist() {
	        driver.findElement(addToWishlistButton).click();
	    }

	    public void addToCart() {
	        driver.findElement(addToCartButton).click();
	    }

	    public boolean isAddedToCart() {
	        return driver.findElement(cartSuccessMessage).isDisplayed();
	    }

	    public boolean isAddedToWishlist() {
	        return driver.findElement(wishlistSuccessMessage).isDisplayed();
	    }
	    
	    public void moveToCheckout() {
	    	driver.findElement(cartButton).click();
	    	driver.findElement(checkoutLink).click();
	    }
}
