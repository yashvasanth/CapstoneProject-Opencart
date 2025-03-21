Feature: OpenCart User Management
  As a user
  I want to register, login, and recover my password
  So that I can manage my account

  Scenario: Register a new user
    Given I am on the registration page
    When I enter valid registration details
    And I accept the privacy policy
    And I click on continue button
    Then I should see that my account has been created

  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter valid email and password
    And I click on login button
    Then I should be logged in successfully

  Scenario: Recover forgotten password
    Given I am on the forgotten password page
    When I enter my email address
    And I click on continue button for password recovery
    Then I should see a confirmation message that an email has been sent
