Feature: Employee List

    Background:
        Given user is on the employee list page

    Scenario: All employees are displayed
    When page loads
    Then user should see all employees

    Scenario: All employees details are displayed correctly
    When page loads
    Then user should see employee details displayed correctly

    Scenario: User should be directed to add employee page
    When user clicks add employee button
    Then user should be directed to add employee page

    Scenario: User should be directed to edit employee page
    When user clicks edit button
    Then user should be directed to edit employee page

    Scenario: User should see remove employee pop up
    When user clicks remove button
    Then user should see remove employee pop up

    Scenario: User accidentally clicks remove but doesnt want to remove employee
    When user clicks remove button
    Then user should see remove employee pop up
    And user clicks cancel
    Then user should still see employee in employee list


    Scenario: User wants to remove employee
    Given a test employee exists
    When user clicks remove button for the test employee
    Then user should see remove employee pop up
    When user clicks confirm Remove
    Then user should see employee is deleted


