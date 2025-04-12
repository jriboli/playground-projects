Feature: Login

A short summary of the feature

@login
Scenario: Test login with valid credentials
	Given I click the login link
	When I enter username as "rocket" and password as "" 
	And I click login button
	Then I should be logged in
