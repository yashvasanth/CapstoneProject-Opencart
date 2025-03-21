Feature: Order Placement

Scenario Outline: Place an Order from Search to Checkout

	Given I search for a "MacBook"
	And I open "MacBook" details page
	And I add it to the cart
	When I go to checkout
	And I choose guest checkout
	And I enter billing details "luci","l","vasanth@example.com","1234567890","123 Street", "Chennai", "600001", "India", "Goa"
	And I continue with shipping
	And I agree to the terms
	And I select the payment method
	And I confirm the order
	Then I should see an order confirmation message