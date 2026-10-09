package steps;

import api.EmployeeApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.EditEmployeePage;
import pages.EmployeeListPage;
import shared.TestContext;

public class EditEmployeeSteps {
    private final TestContext context;
    private EditEmployeePage editEmployeePage;
    private EmployeeListPage employeeListPage;

    public EditEmployeeSteps(TestContext context) {
        this.context = context;
    }

    @Given("a test editing employee exists")
    public void aTestEditingEmployeeExists() throws Exception {

        EmployeeApi employeeApi = new EmployeeApi();

        Long employeeId = employeeApi.createTestEditEmployee();

        context.setTestEditEmployeeId(employeeId);
        employeeListPage = new EmployeeListPage(context.getDriver());
        employeeListPage.refreshPage();
    }

    @Given("user is on the edit employee page")
    public void userIsOnTheEditEmployeePage(){
        editEmployeePage =  new EmployeeListPage(context.getDriver())
                .clickEditEmployee(context.getTestEditEmployeeId().intValue());

    }

    @When("user inputs edited employee details incorrectly")
    public void userInputsEditedEmployeeDetailsIncorrectly(){
        editEmployeePage.enterStartDate("29-01-2030");
        editEmployeePage.clickSaveChangesButton();
    }

    @Then("user should see error message for incorrect field")
    public void userShouldSeeErrorMessageForIncorrectField(){
        Assert.assertTrue(editEmployeePage.getStartDateError().contains("Start date cannot be in the future"));

    }

    @When("user inputs edited employee details correctly")
    public void userInputsEditedEmployeeDetailsCorrectly(){
        editEmployeePage.enterStartDate("29-12-2025");
        editEmployeePage.clickSaveChangesButton();
    }

    @Then("user should see edited employee on employee list page")
    public void userShouldSeeEditedEmployeeOnEmployeeListPage(){

        Assert.assertTrue(employeeListPage.getEmployeeDetails(context.getTestEditEmployeeId().intValue()).contains("Edit"));
    }

    @Then("user should see employee details prefilled on edit page")
    public void userShouldSeeEmployeeDetailsPrefilledOnEditPage(){
        Assert.assertFalse(editEmployeePage.getEmployeeDetails().isEmpty());
    }
}
