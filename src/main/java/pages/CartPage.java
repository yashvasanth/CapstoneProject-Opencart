package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    
    private By productRows = By.cssSelector(".table-responsive tbody tr");
    private By checkoutButton = By.xpath("//*[@id=\"content\"]/div[3]/div[2]/a");
    private By updateButton = By.cssSelector(".btn-primary[data-original-title='Update']");
    private By cartSuccessMessage = By.cssSelector(".alert-success");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        return driver.findElements(By.xpath("//td[contains(text(),'" + productName + "')]")).size() > 0;
    }

    public void removeProduct(String productName) {
        // Find the row containing the product
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//td[contains(text(),'" + productName + "')]/parent::tr")));
        
        // Find and click the remove (red X) button
        WebElement removeButton = row.findElement(By.className("btn-danger"));
        removeButton.click();
        
        // Wait briefly for the UI to register the click
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click the update button (blue refresh button)
        click(updateButton);
        
        // Wait for the success message or for the product to disappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartSuccessMessage));
    }

    public void updateProductQuantity(String productName, int quantity) {
        WebElement row = driver.findElement(By.xpath("//td[contains(text(),'" + productName + "')]/parent::tr"));
        WebElement quantityInput = row.findElement(By.cssSelector("input[type='text']"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        
        
        click(updateButton);
        
        waitForElement(cartSuccessMessage);
    }

    public int getProductCount() {
        List<WebElement> rows = driver.findElements(productRows);
        return rows.size();
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }
}