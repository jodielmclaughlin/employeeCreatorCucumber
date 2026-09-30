package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddEmployeePage extends BasePage{
    
    private By submitButton = By.cssSelector("[data-testid='submit-employee-button']");
    private By firstNameError = By.cssSelector("[data-testid='first-name-error']");
    private By firstNameInput = By.cssSelector("[data-testid='first-name-input']");
    private By lastNameInput = By.cssSelector("[data-testid='last-name-input']");
    private By emailInput = By.cssSelector("[data-testid='email-input']");
    private By phoneNumberInput = By.cssSelector("[data-testid='phone-number-input']");
    private By addressInput = By.cssSelector("[data-testid='address-input']");
    private By contractTypeSelect = By.cssSelector("[data-testid='contract-type-select']");
    private By jobTitleInput = By.cssSelector("[data-testid='job-title-input']");
    private By startDateInput = By.cssSelector("[data-testid='start-date-input']");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }
    
    public void clickSubmit(){
        click(submitButton);
    }

    public String getFirstNameError(){
        return textOf(firstNameError);
    }

    public void enterFirstName(String string){
        type(firstNameInput, string);
    }

    public void enterLastName(String string){
        type(lastNameInput, string);
    }
    public void enterEmail(String string){
        type(emailInput, string);
    }
    public void enterPhoneNumber(String string){
        type(phoneNumberInput, string);
    }
    public void enterAddress(String string){
        type(addressInput, string);
    }
    public void enterJobTitle(String string){
        type(jobTitleInput, string);
    }

    public void enterStartDate(String string){
        type(startDateInput, string);
    }

    public void selectContractType(String visibleText){
        Select contractType = new Select(waitForVisible(contractTypeSelect));
        contractType.selectByVisibleText(visibleText);
    }


}
