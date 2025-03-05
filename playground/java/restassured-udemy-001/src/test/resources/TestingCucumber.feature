Feature: Testing Book Application
  This feature covers the basic operations for book store management.

  Background: User Authorization and generation of token
    Given I am an Authorized User

  Scenario: Authorized User can use book application
    Given A list of books
    When I add a book to list
    Then The book is added