package shared;

import org.openqa.selenium.WebDriver;

import config.ConfigReader;
import config.WebDriverFactory;

public class TestContext {
    
    private WebDriver driver;

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
}
