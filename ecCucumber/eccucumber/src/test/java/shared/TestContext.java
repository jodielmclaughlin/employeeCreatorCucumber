package shared;

import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import config.WebDriverFactory;

public class TestContext {
    
    private WebDriver driver;
    private Long testEmployeeId;

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
}
