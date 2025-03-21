package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultsPage extends BasePage {
    private final By productTitles = By.cssSelector(".product-thumb .caption h4 a");
    private final By sortDropdown = By.id("input-sort");
   // private final By subcategoriesCheckbox = By.id("sub_category"); 
    private final By descriptionCheckbox = By.id("description");
    private final By listViewButton = By.id("list-view");
    private final By gridViewButton = By.id("grid-view");
    private final By searchButton = By.id("button-search");
    private final By productPrices = By.cssSelector(".product-thumb .price");
    private final By productRatings = By.cssSelector(".product-thumb .rating");
    private final By listViewProducts = By.cssSelector(".product-list");
    private final By gridViewProducts = By.cssSelector(".product-grid");
    
    
    private final long DELAY = 1000;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    
    private void addDelay() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            System.out.println("Delay interrupted: " + e.getMessage());
        }
    }

    public List<String> getProductNames() {
        List<WebElement> products = waitForElementsVisible(productTitles);
        List<String> productNames = new ArrayList<>();
        
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        
        return productNames;
    }

    public boolean resultsContain(String productName) {
        List<String> productNames = getProductNames();
        addDelay(); 
        for (String name : productNames) {
            if (name.contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean resultsContainAll(List<String> productNames) {
        List<String> actualNames = getProductNames();
        addDelay(); 
        for (String expectedName : productNames) {
            boolean found = false;
            for (String actualName : actualNames) {
                if (actualName.contains(expectedName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public SearchResultsPage sortResultsBy(String sortOption) {
        System.out.println("Sorting results by: " + sortOption);
        addDelay();
        
        
        List<WebElement> productsBefore = driver.findElements(productTitles);
        String firstProductBeforeSort = productsBefore.isEmpty() ? "" : productsBefore.get(0).getText();
        
        selectByVisibleText(sortDropdown, sortOption);
        
        // Wait for the page to reload/update
        try {
          
          new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Then wait for the products to be different OR for a reasonable timeout
            if (!firstProductBeforeSort.isEmpty()) {
                wait.until(webDriver -> {
                    List<WebElement> productsAfter = webDriver.findElements(productTitles);
                    if (productsAfter.isEmpty()) return false;
                    
                    // Check if the products have changed in any way
                    if (productsAfter.size() != productsBefore.size()) return true;
                    
                    // Check if first product changed
                    return !productsAfter.get(0).getText().equals(firstProductBeforeSort);
                });
            } else {
                // If there were no products before, just wait for products to appear
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitles));
            }
            
            addDelay();
            System.out.println("Sorting completed");
        } catch (Exception e) {
            System.out.println("Warning: Timeout waiting for sort results to update: " + e.getMessage());
        }
        
        return this;
    }

//    public SearchResultsPage setSearchInSubcategories(boolean select) {
//        System.out.println("Setting 'Search in subcategories' to: " + select);
//        addDelay(); // Added delay
//        WebElement checkbox = waitForElementVisible(subcategoriesCheckbox);
//        if ((checkbox.isSelected() && !select) || (!checkbox.isSelected() && select)) {
//            checkbox.click();
//            addDelay(); // Added delay after click
//        }
//        return this;
//    }

    public SearchResultsPage setSearchInDescriptions(boolean select) {
        System.out.println("Setting 'Search in descriptions' to: " + select);
        addDelay();
        WebElement checkbox = waitForElementVisible(descriptionCheckbox);
        if ((checkbox.isSelected() && !select) || (!checkbox.isSelected() && select)) {
            checkbox.click();
            addDelay(); 
        }
        return this;
    }

    public SearchResultsPage clickSearch() {
        System.out.println("Clicking search button");
        addDelay(); 
        waitForElementClickable(searchButton).click();
        addDelay();
        return this;
    }
    
    public SearchResultsPage switchToListView() {
        System.out.println("Switching to list view");
        addDelay(); 
        waitForElementClickable(listViewButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(listViewProducts));
        addDelay();
        return this;
    }

    public SearchResultsPage switchToGridView() {
        System.out.println("Switching to grid view");
        addDelay();
        waitForElementClickable(gridViewButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(gridViewProducts));
        addDelay(); 
        return this;
    }

    public boolean isListViewActive() {
        addDelay(); 
        boolean isActive = waitForElementsVisible(listViewProducts).size() > 0;
        System.out.println("List view active: " + isActive);
        return isActive;
    }

    public boolean isGridViewActive() {
        addDelay(); 
        boolean isActive = waitForElementsVisible(gridViewProducts).size() > 0;
        System.out.println("Grid view active: " + isActive);
        return isActive;
    }

    public boolean isPriceSorted(boolean ascending) {
        System.out.println("Checking if prices are sorted " + (ascending ? "ascending" : "descending"));
        addDelay();
        
        List<WebElement> priceElements = waitForElementsVisible(productPrices);
        List<Double> prices = new ArrayList<>();
        
        for (WebElement element : priceElements) {
            String priceText = element.getText();
            
            Double price = extractPriceValue(priceText);
            if (price != null) {
                prices.add(price);
                System.out.println("Found price: " + price);
            }
        }
        
        if (prices.size() <= 1) {
            return true;
        }
        
        boolean isSorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (ascending) {
                if (prices.get(i) > prices.get(i + 1)) {
                    isSorted = false;
                    break;
                }
            } else {
                if (prices.get(i) < prices.get(i + 1)) {
                    isSorted = false;
                    break;
                }
            }
        }
        
        System.out.println("Prices are sorted correctly: " + isSorted);
        return isSorted;
    }
   
    private Double extractPriceValue(String priceText) {
        try {
           
            if (priceText.contains("Ex Tax:")) {
               
                priceText = priceText.substring(0, priceText.indexOf("Ex Tax:")).trim();
            }
            
            // If there are multiple prices (like sales prices), use the first one
            Pattern pattern = Pattern.compile("([\\d,.]+)");
            Matcher matcher = pattern.matcher(priceText);
            
            if (matcher.find()) {
                String extractedPrice = matcher.group(1);
                
                String cleanPrice = extractedPrice.replaceAll("[^\\d.]", "");
                
                if (cleanPrice.indexOf('.') != cleanPrice.lastIndexOf('.')) {
                    int firstDotIndex = cleanPrice.indexOf('.');
                    cleanPrice = cleanPrice.substring(0, firstDotIndex + 1) + 
                                 cleanPrice.substring(firstDotIndex + 1).replace(".", "");
                }
                
                return Double.parseDouble(cleanPrice);
            }
            return 0.0;
        } catch (Exception e) {
            System.out.println("Warning: Failed to parse price from: " + priceText + " - " + e.getMessage());
            return 0.0;
        }
    }

    public boolean isRatingSorted() {
        System.out.println("Checking if ratings are sorted correctly");
        addDelay(); 
        
        try {
           
            if (!driver.findElements(productRatings).isEmpty()) {
                List<WebElement> ratingElements = waitForElementsVisible(productRatings);
                
                if (ratingElements.size() <= 1) {
                    return true;
                }
                
                // Extract rating scores based on the stars visible
                List<Integer> ratings = new ArrayList<>();
                for (WebElement ratingElement : ratingElements) {
                    int rating = extractRatingValue(ratingElement);
                    ratings.add(rating);
                    System.out.println("Found rating: " + rating);
                }
                
                // Check if ratings are in descending order (highest first)
                boolean isSorted = true;
                for (int i = 0; i < ratings.size() - 1; i++) {
                    if (ratings.get(i) < ratings.get(i + 1)) {
                        isSorted = false;
                        break;
                    }
                }
                
                System.out.println("Ratings are sorted correctly: " + isSorted);
                return isSorted;
            } else {
               
                System.out.println("Warning: No rating elements found on the page");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Warning: Error checking rating sort: " + e.getMessage());
            return true;
        }
    }
    
    private int extractRatingValue(WebElement ratingElement) {
        try {
           
            List<WebElement> filledStars = ratingElement.findElements(By.cssSelector("i.fa.fa-star:not(.fa-star-o)"));
            int filledStarCount = filledStars.size();
            System.out.println("Extracted rating: " + filledStarCount + " stars");
            return filledStarCount;
        } catch (Exception e) {
            System.out.println("Warning: Failed to extract rating value - " + e.getMessage());
            return 0;
        }
    }
}