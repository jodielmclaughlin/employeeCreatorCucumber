package shared;


import api.AuthApi;
import api.EmployeeApi;
import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class Hooks {

    private TestContext context;
    private EmployeeApi employeeApi;
    private AuthApi authApi;
    
    public Hooks(TestContext context, EmployeeApi employeeApi, AuthApi authApi){
        this.context = context;
        this.employeeApi = employeeApi;
        this.authApi = authApi;
    }
    
    @Before 
    public void setup(Scenario scenario) throws Exception{
        context.startDriver();
        if(scenario.getSourceTagNames().contains("@admin")){

            String token = authApi.login(
                    ConfigReader.adminEmail(),
                    ConfigReader.adminPassword()
            );
            context.authenticateBrowser(token);
        } else if (scenario.getSourceTagNames().contains("@employee")) {
            String token = authApi.login(
                    ConfigReader.employeeEmail(),
                    ConfigReader.employeePassword()
            );
            context.authenticateBrowser(token);
        }
    }

    @After 
    public void teardown(Scenario scenario) throws Exception {
        WebDriver driver = context.getDriver();

        if (driver != null) {
            if (scenario.isFailed()) {
                System.out.println("=== Browser console logs for: "
                        + scenario.getName() + " ===");

                try {
                    for (LogEntry entry :
                            driver.manage().logs().get(LogType.BROWSER).getAll()) {
                        System.out.println(
                                entry.getLevel() + ": " + entry.getMessage()
                        );
                    }
                } catch (Exception e) {
                    System.out.println(
                            "Could not retrieve browser logs: " + e.getMessage()
                    );
                }
            }

            driver.quit();
        }

        if (context.getTestEmployeeId() != null) {
            employeeApi.deleteEmployee(context.getTestEmployeeId());
            context.setTestEmployeeId(null);
        }
        if (context.getTestEditEmployeeId() != null) {
            employeeApi.deleteEmployee(context.getTestEditEmployeeId());
            context.setTestEditEmployeeId(null);
        }
        context.quitDriver();
    }

}
