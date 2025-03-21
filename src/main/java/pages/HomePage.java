package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;;

public class HomePage extends BasePage {
    private WebDriverWait wait;

    private By searchBox = By.name("search");
    private final By searchField = By.name("search");
    private final By searchButton = By.cssSelector(".btn-default");
    
    private By cartLink = By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span");
//  private By laptopsCategory = By.xpath("//a[contains(text(),'Laptops & Notebooks')]");
//  private By productCategories = By.cssSelector(".nav-item");
  private By searchBox1 = By.name("search");
  private By searchButton1 = By.cssSelector("button.btn-default");
 
    public HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(searchInput, "value"));
        searchInput.sendKeys(Keys.ENTER); 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'" + productName + "')]")));
    }
    
    public SearchResultsPage searchForProduct(String productName) {
        waitForElementVisible(searchField).sendKeys(productName);
        waitForElementClickable(searchButton).click();
        return new SearchResultsPage(driver);
    }
    
    public void navigateToHomePage(String url) {
        driver.get(url);
      
    }

    public void navigateToCategory(String category) {
        waitForElement(By.xpath("//a[contains(text(),'" + category + "')]")).click();
    }

    public void searchForProduct1(String productName) {
        waitForElement(searchBox1).clear();
        sendKeys(searchBox1, productName);
        click(searchButton1);
    }

    public void clickOnProduct(String productName) {
        click(By.xpath("//a[contains(text(),'" + productName + "')]"));
    }

    public void navigateToCart() {
        click(cartLink);
    }
    
    public boolean isProductDisplayedInSearchResults(String productName) {
        return driver.findElements(By.xpath("//div[contains(@class,'product-layout')]//a[contains(text(),'" + productName + "')]")).size() > 0;
    }
    
    public void clickOnProductInSearchResults(String productName) {
        click(By.xpath("//div[contains(@class,'product-layout')]//a[contains(text(),'" + productName + "')]"));
    }
}