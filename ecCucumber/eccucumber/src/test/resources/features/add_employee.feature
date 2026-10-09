@admin
Feature: Add Employee

    Background:
        Given user is on the add employee page

    Scenario: user tries to add employee unsuccessfully
    When user inputs employee details incorrectly
    Then user should see error message


    Scenario: user successfully adds employee
    When user inputs employee details correctly
    Then user should see new employee on employee list page