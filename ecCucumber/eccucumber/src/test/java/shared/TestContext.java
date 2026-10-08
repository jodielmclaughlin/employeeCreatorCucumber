package shared;

import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import config.WebDriverFactory;

public class TestContext {
    
    private WebDriver driver;
    private Long testEmployeeId;
    private String testEmployeeEmail;
    private String testEmployeePhoneNumber;

    private Long testEditEmployeeId;
    private String testEditEmployeeEmail;
    private String testEditEmployeePhoneNumber;

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

    public String getTestEmployeePhoneNumber() {
        return testEmployeePhoneNumber;
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

    public String getTestEditEmployeeEmail() {
        return testEditEmployeeEmail;
    }

    public void setTestEditEmployeeEmail(String testEmployeeEmail) {
        this.testEditEmployeeEmail = testEmployeeEmail;
    }

    public String getTestEditEmployeePhoneNumber() {
        return testEditEmployeePhoneNumber;
    }

    public void setTestEditEmployeePhoneNumber(String testEmployeePhoneNumber) {
        this.testEditEmployeePhoneNumber = testEmployeePhoneNumber;
    }
}
