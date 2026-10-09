package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class EditEmployeePage extends BasePage{

    private By saveChangesButton = By.cssSelector("[data-testid='submit-employee-button']");
    private By startDateInput = By.cssSelector("[data-testid='start-date-input']");
    private By startDateError = By.cssSelector("[data-testid='start-date-error']");
    private By firstNameInput = By.cssSelector("[data-testid='first-name-input']");
    private By lastNameInput = By.cssSelector("[data-testid='last-name-input']");
    private By emailInput = By.cssSelector("[data-testid='email-input']");
    private By phoneNumberInput = By.cssSelector("[data-testid='phone-number-input']");
    private By addressInput = By.cssSelector("[data-testid='address-input']");
    private By contractTypeSelect = By.cssSelector("[data-testid='contract-type-select']");
    private By jobTitleInput = By.cssSelector("[data-testid='job-title-input']");

    public EditEmployeePage(WebDriver driver) {
        super(driver);
    }

    public EmployeeListPage clickSaveChangesButton(){
        click(saveChangesButton);
        return new EmployeeListPage(driver);
    }

    public void enterStartDate(String string){
        waitForVisible(startDateInput);
        type(startDateInput, string);
    }

    public String getStartDateError(){

        return textOf(startDateError);
    }

    public List<String> getEmployeeDetails(){
        List<String> employeeDetails = new ArrayList<>();
        String firstName = textOf(firstNameInput);
        String lastName = textOf(lastNameInput);
        String email = textOf(emailInput);
        String phoneNumber = textOf(phoneNumberInput);
        String address = textOf(addressInput);
        String contractType = textOf(contractTypeSelect);
        String jobTitle = textOf(jobTitleInput);
        String startDate = textOf(startDateInput);
        employeeDetails.add(firstName);
        employeeDetails.add(lastName);
        employeeDetails.add(email);
        employeeDetails.add(phoneNumber);
        employeeDetails.add(address);
        employeeDetails.add(contractType);
        employeeDetails.add(jobTitle);
        employeeDetails.add(startDate);

        return employeeDetails;
    }


}
