Feature: Login

  Background:
    Given user is on the login page

  Scenario: Successful Login
    When user enters correct email
    And user enters password
    And user clicks login button
    Then user should see employee list page

  Scenario: Login with incorrect password
    When user enters correct email
    And user enters incorrect password
    And user clicks login button
    Then user should see invalid credentials error

  Scenario: Login with incorrect email and password
    When user enters incorrect email
    And user enters incorrect password
    And user clicks login button
    Then user should see invalid credentials error