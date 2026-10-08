 Feature: Edit employee

     Background:
         Given user is logged in as an admin
       Given a test editing employee exists
       Given user is on the edit employee page


   Scenario: user should see employee details prefilled on edit page
     Then user should see employee details prefilled on edit page

   Scenario: user tries to edit employee unsuccessfully
     When user inputs edited employee details incorrectly
     Then user should see error message for incorrect field

   Scenario: user successfully edits employee
     When user inputs edited employee details correctly
     Then user should see edited employee on employee list page