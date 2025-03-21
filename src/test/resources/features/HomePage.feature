Feature: Home Page Functionality
 
  Scenario: Verify the homepage loads successfully
    Given User is on the Opencart homepage

  Scenario Outline: Search for a product
    Given User is on the Opencart homepage
    When User searches for "<product>"
    Then Search results for "<product>" should be displayed

    Examples:
      | product     |
      | iPhone      |
      | MacBook     |
