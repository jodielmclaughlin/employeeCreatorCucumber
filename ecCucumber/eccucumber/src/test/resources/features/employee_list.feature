@employee
Feature: Employee List

    Background:
        Given user is on the employee list page

    Scenario: No employees are displayed
    When user logs out
    And user goes to home page
    Then user should not see any employees

    Scenario: All employees are displayed
    When page loads
    Then user should see all employees

    Scenario: All employees details are displayed correctly
    Given a test editing employee exists
    When page loads
    Then user should see employee details displayed correctly

    Scenario: Edit Employee button should be disabled
    Given a test editing employee exists
    Then user should see Edit button is disabled

    Scenario: Add Employee button should be disabled
    Given a test edit employee exists
    Then user should see Add Employee button is disabled

    Scenario: Remove Employee button should be disabled
    Given a test employee exists
    Then user should see Remove Employee button is disabled


