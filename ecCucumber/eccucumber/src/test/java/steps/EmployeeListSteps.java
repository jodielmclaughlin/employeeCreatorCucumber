package steps;

import org.testng.Assert;

import api.EmployeeApi;
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

    @Given("a test employee exists")
    public void aTestEmployeeExists() throws Exception {

        EmployeeApi employeeApi = new EmployeeApi();

        Long employeeId = employeeApi.createTestEmployee();

        context.setTestEmployeeId(employeeId);
        employeeListPage.refreshPage();
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
        employeeListPage.clickRemoveEmployee(250);
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
        employeeListPage.getEmployeeCard(250);
        Assert.assertTrue(employeeListPage.getEmployeeDetails(250).contains("Tammara"));
        Assert.assertFalse(employeeListPage.isEmployeeCardNotDisplayed(250));
    }
    @When("user clicks remove button for the test employee")
    public void userClicksRemoveButtonForTestEmployee() {

        employeeListPage.clickRemoveEmployee(context.getTestEmployeeId().intValue());
    }

    @When("user clicks confirm Remove")
    public void userClicksConfirmRemove() {
        employeeListPage.confirmRemoveEmployee();
    }

    @Then("user should see employee is deleted")
    public void userShouldSeeEmployeeIsDeleted() {
        
        Assert.assertTrue(employeeListPage.getRemoveEmployeeConfirmation().contains("has been removed"));
        employeeListPage.refreshPage();
        Assert.assertTrue(employeeListPage.isEmployeeCardNotDisplayed(context.getTestEmployeeId().intValue()));
    }
}