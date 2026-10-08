package pages;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By emailInput =  By.cssSelector("[data-testid='email-login-input']");
    private By passwordInput =  By.cssSelector("[data-testid='password-login-input']");
    private By loginBtn =  By.cssSelector("[data-testid='submit-login-button']");
    private By error =  By.cssSelector("[data-testid='login-error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void typeInEmailInput(String email){
        type(emailInput, email);
    }

    public void typeInPasswordInput(String password){
        type(passwordInput, password);
    }

    public void userClicksLoginButton(){
        click(loginBtn);
    }


    public EmployeeListPage successfullyLoginAsEmployee(){
        type(emailInput, ConfigReader.employeeEmail());
        type(passwordInput, ConfigReader.employeePassword());
        click(loginBtn);
        return new EmployeeListPage(driver);
    }

    public EmployeeListPage successfullyLoginAsAdmin(){
        type(emailInput, ConfigReader.adminEmail());
        type(passwordInput, ConfigReader.adminPassword());
        click(loginBtn);
        return new EmployeeListPage(driver);
    }

    public LoginPage attemptLoginAs(String email, String password){
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);
        return this;
    }

    public String getErrorMessage(){
        return textOf(error);
    }

}
