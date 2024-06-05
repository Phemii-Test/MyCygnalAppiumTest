
Feature: Login
  I want to be able to login to my Phenome account

  Scenario: Account login
    Given I enter my valid email address
    And a valid password
    When I click on the login button
    Then I should see my dashboard page.
   

