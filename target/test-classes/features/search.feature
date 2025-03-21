
Feature: Product Search and Sort Functionality
As a user of the OpenCart website
I want to search for products and sort results
So that I can find specific products easily

Background:
Given I am on the OpenCart homepage

Scenario: Search for a product by name
When I search for "iPhone"
Then I should see search results containing "iPhone"

Scenario: Search for Mac products
When I search for "Mac"
Then I should see Mac-related products
And the results should include "iMac", "MacBook", "MacBook Air", and "MacBook Pro"

Scenario: Sort search results by price (High to Low)
When I search for "Mac"
And I sort results by "Price (High > Low)"
Then I should see results sorted from highest to lowest price

Scenario: Sort search results by rating
When I search for "Mac"
And I sort results by "Rating (Highest)"
Then I should see results sorted by highest rating first

#Scenario: Search with subcategories option
#When I search for "Mac"
#And I select the "Search in subcategories" option
#And I click the Search button
#Then I should see results from all subcategories

Scenario: Switch between grid and list view
When I search for "Mac"
And I click on the list view button
Then I should see products displayed in list format
When I click on the grid view button
Then I should see products displayed in grid format