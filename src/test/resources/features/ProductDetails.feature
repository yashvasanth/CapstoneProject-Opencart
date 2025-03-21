Feature: Product Page  

  Scenario: Check product details  
    Given I open a product page  
    Then I should see the product name  
    And I should see the product details  
    And I should see the product price  
    And I should see the product image  
    And I should see if the product is in stock  

  Scenario: Add product to wishlist  
    Given I open a product page  
    When I click Add to Wishlist  
    Then I should see a success message of wishlist  

  Scenario: Add product to cart  
    Given I open a product page  
    When I click Add to Cart 
    Then I should see a success message of cart
