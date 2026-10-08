package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
   public static WebDriver createWebDriver(String browser){
    switch(browser.toLowerCase()){
        case "chrome":
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            return new ChromeDriver(options);
         case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser " + browser);
    }
   } 
}
