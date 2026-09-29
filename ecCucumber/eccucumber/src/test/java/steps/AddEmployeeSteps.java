package steps;

import io.cucumber.java.en.Given;
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
        addEmployeePage = new EmployeeListPage(context.getDriver()).clickAddEmployeeButton();
     }

    
}
