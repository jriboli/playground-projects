Feature: Cart

A short summary of the feature

@cart
Scenario: Test cart with no items 
	Given I click the cart link
	When I dont add any items to cart
	Then [outcome]
