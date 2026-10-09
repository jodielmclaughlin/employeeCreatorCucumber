package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import api.EmployeeApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.EditEmployeePage;
import pages.EmployeeListPage;
import pages.LoginPage;
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
        employeeListPage = new EmployeeListPage(context.getDriver());
        employeeListPage.refreshPage();
    }
    @Given("a test edit employee exists")
    public void aTestEditEmployeeExists() throws Exception {

        EmployeeApi employeeApi = new EmployeeApi();

        Long employeeId = employeeApi.createTestEditEmployee();

        context.setTestEditEmployeeId(employeeId);
        employeeListPage = new EmployeeListPage(context.getDriver());
        employeeListPage.refreshPage();
    }

    @When("page loads")
    public void pageLoads(){
        employeeListPage.getTitleText();
    }

    @Then("user should not see any employees")
    public void userShouldNotSeeAnyEmployees(){
        Assert.assertTrue(employeeListPage.checkIfEmployeeCardsArePresent().isEmpty());
    }

    @Then("user should see all employees")
    public void userShouldSeeAllEmployees(){
        Assert.assertFalse(employeeListPage.getAllEmployeeCards().isEmpty());
    }

    @Then("user should see employee details displayed correctly")
    public void userShouldSeeDetailsDisplayedCorrectly(){
        String employee = employeeListPage.getEmployeeDetails(context.getTestEditEmployeeId().intValue());
        Assert.assertFalse(employee.isEmpty());
    }

    @When("user clicks add employee button")
    public void userClicksAddEmployeeButton() {
        employeeListPage.clickAddEmployeeButton();
    }

    @Then("user should be directed to add employee page")
    public void userShouldBeDirectedToAddEmployeePage() {
        addEmployeePage = new AddEmployeePage(context.getDriver());
        Assert.assertEquals(addEmployeePage.currentUrl(), "http://localhost:5173/#/employees/new");
    }
    
    @When("user clicks edit button")
    public void userClicksEditButton() {
        employeeListPage.clickEditEmployee(context.getTestEditEmployeeId().intValue());
    }

    @Then("user should be directed to edit employee page")
    public void userShouldBeDirectedToEditEmployeePage() {
        editEmployeePage = new EditEmployeePage(context.getDriver());
        String employeeId = context.getTestEditEmployeeId().toString();
        Assert.assertEquals(editEmployeePage.currentUrl(), "http://localhost:5173/#/employees/"+ employeeId +"/edit");
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
        employeeListPage.getEmployeeCard(context.getTestEmployeeId().intValue());
        Assert.assertTrue(employeeListPage.getEmployeeDetails(context.getTestEmployeeId().intValue()).contains("Delete"));
        Assert.assertFalse(employeeListPage.isEmployeeCardNotDisplayed(context.getTestEmployeeId().intValue()));
    }
    @When("user clicks remove button for the test employee")
    public void userClicksRemoveButtonForTestEmployee() {
        employeeListPage.clickRemoveEmployee(context.getTestEmployeeId().intValue());
    }

    @When("user clicks confirm Remove")
    public void userClicksConfirmRemove() {
        employeeListPage.confirmRemoveEmployee();

    }

    @When("user logs out")
    public void userLogsOut(){
        employeeListPage.clickLogoutButton();
    }

    @When("user goes to home page")
    public void userGoesToHomePage(){
        employeeListPage.clickHomeButton();
    }

    @Then("user should see employee is deleted")
    public void userShouldSeeEmployeeIsDeleted() {
        
        Assert.assertTrue(employeeListPage.getRemoveEmployeeConfirmation().contains("has been removed"));
        employeeListPage.refreshPage();
        Assert.assertTrue(employeeListPage.isEmployeeCardNotDisplayed(context.getTestEmployeeId().intValue()));
        context.setTestEmployeeId(null);
    }

    @Then("user should see Edit button is disabled")
    public void userShouldSeeEditButtonIsDisabled(){
        WebElement editButton = employeeListPage.getEditButton(context.getTestEditEmployeeId().intValue());
        Assert.assertFalse(editButton.isEnabled());
    }

    @Then("user should see Add Employee button is disabled")
    public void userShouldSeeAddEmployeeButtonIsDisabled(){
        WebElement addButton = employeeListPage.getAddButton();
        Assert.assertFalse(addButton.isEnabled());
    }

    @Then("user should see Remove Employee button is disabled")
    public void userShouldSeeRemoveEmployeeButtonIsDisabled(){
        WebElement removeButton = employeeListPage.getRemoveButton(context.getTestEmployeeId().intValue());
        Assert.assertFalse(removeButton.isEnabled());
    }
}