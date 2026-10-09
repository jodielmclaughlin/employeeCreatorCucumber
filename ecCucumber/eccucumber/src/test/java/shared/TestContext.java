package shared;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import config.WebDriverFactory;

public class TestContext {
    
    private WebDriver driver;
    private Long testEmployeeId;
    private String testEmployeeEmail;
    private String testEmployeePhoneNumber;

    private Long testEditEmployeeId;


    public void startDriver(){
        driver = WebDriverFactory.createWebDriver(ConfigReader.browser());
        driver.get(ConfigReader.baseUrl());
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void  quitDriver(){
        if(driver != null){
                driver.quit();
            }
    }
    public Long getTestEmployeeId() {
        return testEmployeeId;
    }

    public void setTestEmployeeId(Long testEmployeeId) {
        this.testEmployeeId = testEmployeeId;
    }

    public String getTestEmployeeEmail() {
        return testEmployeeEmail;
    }

    public void setTestEmployeeEmail(String testEmployeeEmail) {
        this.testEmployeeEmail = testEmployeeEmail;
    }

    public void setTestEmployeePhoneNumber(String testEmployeePhoneNumber) {
        this.testEmployeePhoneNumber = testEmployeePhoneNumber;
    }

    public Long getTestEditEmployeeId() {
        return testEditEmployeeId;
    }

    public void setTestEditEmployeeId(Long testEmployeeId) {
        this.testEditEmployeeId = testEmployeeId;
    }

    public void authenticateBrowser(String token) {
        driver.get(ConfigReader.baseUrl());

        ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('access_token', arguments[0]);",
                token
        );

        String storedToken = (String) ((JavascriptExecutor) driver)
                .executeScript(
                        "return localStorage.getItem('access_token');"
                );

        System.out.println("Token stored: " + (storedToken != null));
        System.out.println("Token length: " +
                (storedToken == null ? 0 : storedToken.length()));

        driver.navigate().refresh();

        System.out.println("URL after refresh: " + driver.getCurrentUrl());
    }

}
