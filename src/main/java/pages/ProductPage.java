package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    
    private By addToCartButton = By.id("button-cart");
    private By cartLink = By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span");
    private By successMessage = By.cssSelector(".alert-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        click(addToCartButton);
        waitForElement(successMessage);
    }

    public void navigateToCart() {
        click(cartLink);
    }
    
    
}
