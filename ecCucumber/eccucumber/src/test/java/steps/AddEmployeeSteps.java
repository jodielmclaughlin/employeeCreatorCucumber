package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.EmployeeListPage;
import shared.TestContext;

public class AddEmployeeSteps {
    
    private final TestContext context;
    private AddEmployeePage addEmployeePage;

    public AddEmployeeSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is on the add employee page")
     public void userIsOnTheAddEmployeePage(){
        addEmployeePage = new EmployeeListPage(context.getDriver())
                .clickAddEmployeeButton();
     }

     @When("user inputs employee details incorrectly")
     public void userInputsEmployeeDetailsIncorrectly(){
        addEmployeePage.clickSubmit();
     }

     @Then("user should see error message")
     public void userShouldSeeErrorMessage(){
        Assert.assertTrue(addEmployeePage.getFirstNameError().contains("must not be blank"));
     }

     @When("user inputs employee details correctly")
     public void userInputsEmployeeDetailsCorrectly(){
        String uniqueValue = String.valueOf(System.currentTimeMillis());
        
        String email = "selenium." + uniqueValue + "@example.com";
        String phoneNumber = "07" + uniqueValue.substring(uniqueValue.length() - 9);

        context.setTestEmployeeEmail(email);
        context.setTestEmployeePhoneNumber(phoneNumber);

        addEmployeePage.enterFirstName("Selenium");
        addEmployeePage.enterLastName("Test");
        addEmployeePage.enterEmail(email);
        addEmployeePage.enterPhoneNumber(phoneNumber);
        addEmployeePage.enterAddress("1 Test Street");
        addEmployeePage.selectContractType("Full Time");
        addEmployeePage.enterJobTitle("Test Engineer");
        addEmployeePage.enterStartDate("01-01-2025");

        addEmployeePage.clickSubmit();
     }

     @Then("user should see new employee on employee list page")
     public void userShouldSeeNewEmployeeOnEmployeeListPage(){
         EmployeeListPage employeeListPage = new EmployeeListPage(context.getDriver());
         Assert.assertTrue(employeeListPage.isEmployeeDisplayedByEmail(context.getTestEmployeeEmail()));
     }
    
}
