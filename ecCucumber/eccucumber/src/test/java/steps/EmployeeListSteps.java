package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.EditEmployeePage;
import pages.EmployeeListPage;
import shared.TestContext;

public class EmployeeListSteps {
    private final TestContext context;
    private EmployeeListPage employeeListPage;
    private EditEmployeePage editEmployeePage;
    private AddEmployeePage addEmployeePage;
    
    
    public EmployeeListSteps(TestContext context){
        this.context =  context;
    }

    @Given("user is on the employee list page")
    public void userIsOnEmployeeListPage(){
        employeeListPage = new EmployeeListPage(context.getDriver());
    }

    @When("page loads")
    public void pageLoads(){
        employeeListPage.getTitleText();
    }

    @Then("user should see all employees")
    public void userShouldSeeAllEmployees(){ 
        Assert.assertTrue(!employeeListPage.getAllEmployeeCards().isEmpty());
    }

    @Then("user should see employee details displayed correctly")
    public void userShouldSeeDetailsDisplayedCorrectly(){
        String employee = employeeListPage.getEmployeeDetails(250);
        Assert.assertTrue(employee.contains("Tammara"));
        Assert.assertTrue(employee.contains("Orchestrator"));
        Assert.assertTrue(employee.contains("jenelle.hermiston@hotmail.com"));
    }
    @When("user clicks add employee button")
    public void userClicksAddEmployeeButton() {
        employeeListPage.clickAddEmployeeButton();
    }

    @Then("user should be directed to add employee page")
    public void userShouldBeDirectedToAddEmployeePage() {
        addEmployeePage = new AddEmployeePage(context.getDriver());
        Assert.assertEquals("http://localhost:5173/#/employees/new", addEmployeePage.currentUrl());
    }
    
    @When("user clicks edit button")
    public void userClicksEditButton() {
        employeeListPage.clickEditEmployee(250);
    }
    @Then("user should be directed to edit employee page")
    public void userShouldBeDirectedToEditEmployeePage() {
        editEmployeePage = new EditEmployeePage(context.getDriver());
        Assert.assertEquals("http://localhost:5173/#/employees/250/edit", editEmployeePage.currentUrl());
    }

    @When("user clicks remove button")
    public void userClicksRemoveButton() {
        employeeListPage.clickRemoveEmployee(269);
    }

    @Then("user should see remove employee pop up")
    public void userShouldSeeRemoveEmployeePopUp() {
        Assert.assertTrue(employeeListPage.getRemovePopUp().getText().contains("Are you sure you want to remove"));
    }

    @When("user clicks cancel")
    public void userClicksCancel() {
        employeeListPage.cancelRemoveEmployee();
    }

    @Then("user should still see employee in employee list")
    public void userShouldStillSeeEmployeeInEmployeeList() {
        employeeListPage.getEmployeeCard(269);
        Assert.assertTrue(employeeListPage.getEmployeeDetails(269).contains("Lacy"));
        Assert.assertFalse(employeeListPage.isEmployeeCardNotDisplayed(269));
    }

    @When("user clicks confirm Remove")
    public void userClicksConfirmRemove() {
        employeeListPage.confirmRemoveEmployee();
    }

    @Then("user should see employee is deleted")
    public void userShouldSeeEmployeeIsDeleted() {
        Assert.assertTrue(employeeListPage.getRemoveEmployeeConfirmation().contains("has been removed"));
    }
}