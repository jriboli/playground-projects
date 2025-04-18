Feature: HomePage

A short summary of the feature

@home
Scenario: Test login with valid credentials
	Given I am on the HomePage
    When I click the Store button
    Then I should be on the Store Page
