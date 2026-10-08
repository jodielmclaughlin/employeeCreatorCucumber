package steps;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.EmployeeListPage;
import pages.LoginPage;
import shared.TestContext;

public class LoginSteps {

    private final TestContext context;
    private LoginPage loginPage;
    private EmployeeListPage employeeListPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage(){
        loginPage = new EmployeeListPage(context.getDriver()).clickLoginButton();
    }

    @When("user enters correct email")
    public void userEntersCorrectEmail(){
        loginPage.typeInEmailInput(ConfigReader.adminEmail());
    }

    @When("user enters password")
    public void userEntersPassword(){
        loginPage.typeInPasswordInput(ConfigReader.adminPassword());
    }

    @When("user clicks login button")
    public void userClicksLoginButton(){
        loginPage.userClicksLoginButton();
    }

    @Then("user should see employee list page")
    public void userShouldSeeEmployeeListPage(){
        employeeListPage = new EmployeeListPage(context.getDriver());
        System.out.println(context.getDriver().getCurrentUrl());
        Assert.assertEquals(context.getDriver().getCurrentUrl(), "http://localhost:5173/#/");
    }

    @When("user enters incorrect password")
    public void userEntersIncorrectPassword(){
        loginPage.typeInPasswordInput("wrongpassword");
    }

    @When("user enters incorrect email")
    public void userEntersIncorrectEmail(){
        loginPage.typeInEmailInput("wrongemail@wrong.com");
    }

    @Then("user should see invalid credentials error")
    public void userShouldSeeInvalidCredentialsError(){
        String expectedErrorMessage = "Invalid email or password";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }



}
