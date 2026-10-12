package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class WebDriverFactory {
   public static WebDriver createWebDriver(String browser){
    switch(browser.toLowerCase()){
        case "chrome":
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.BROWSER, Level.ALL);

            options.setCapability("goog:loggingPrefs", logs);
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
