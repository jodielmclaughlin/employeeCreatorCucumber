package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeeListPage extends BasePage{

    public By title = By.cssSelector("[data-testid='employee-list-page']");
    public By addEmployeeButton = By.cssSelector("[data-testid='add-employee-button']");
    public By removePopUp = By.cssSelector("[data-testid='delete-confirmation-modal']");
    public By rmPopUpCancelButton = By.cssSelector("[data-testid='cancel-delete-button']");
    public By rmPopUpRemoveButton = By.cssSelector("[data-testid='confirm-delete-button']");
    public By employeeCards = By.cssSelector("[data-testid^='employee-card-']");
    public By removeEmployeeConfirmation = By.cssSelector("[data-testid='employee-message']");


    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText(){
        return textOf(title);
    }

    public List<WebElement> getAllEmployeeCards(){
        return waitForAllVisible(employeeCards);
    }

    public String getEmployeeDetails(long employeeId){
        return waitForVisible(getEmployeeCard(employeeId)).getText();
    }
    
    public By editButton(int employeeId) {
        return By.cssSelector(
            "[data-testid='edit-employee-" + employeeId + "']"
        );
    }

    public By removeButton(int employeeId) {
        return By.cssSelector("[data-testid='delete-employee-" + employeeId + "']");
    }

    public void clickEditEmployee(int employeeId) {
        click(editButton(employeeId));
    }

    public void clickRemoveEmployee(int employeeId) {
        click(removeButton(employeeId));
    }
    public By getEmployeeCard(long employeeId){
        return By.cssSelector("[data-testid='employee-card-" + employeeId + "']");
    }

    public void clickAddEmployeeButton(){
        click(addEmployeeButton);
    }
    public WebElement getRemovePopUp(){
        return waitForVisible(removePopUp);
    }

    public void cancelRemoveEmployee(){
        waitForVisible(removePopUp);
        click(rmPopUpCancelButton);
    }

    public void confirmRemoveEmployee(){
        waitForVisible(removePopUp);
        click(rmPopUpRemoveButton);
    }
    public String getRemoveEmployeeConfirmation(){
        return textOf(removeEmployeeConfirmation);
    }
    public boolean isEmployeeCardNotDisplayed(int employeeId) {
    return driver.findElements(getEmployeeCard(employeeId)).isEmpty();
}
}
