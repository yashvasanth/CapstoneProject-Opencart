Feature: Shopping Cart Functionality

  Scenario: Add multiple products to cart and remove one before checkout
    Given User is on the OpenCart homepage
    When User navigates to "Laptops & Notebooks" category
    And User adds "MacBook" to cart
    And User searches for "mac" in the search bar
    And User adds "MacBook Pro" to cart
    And User navigates to shopping cart
    Then User should see 2 products in the cart
    When User removes "MacBook" from the cart
    Then User should see 2 product in the cart
    And User proceeds to checkout