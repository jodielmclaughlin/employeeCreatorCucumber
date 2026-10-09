package shared;


import api.AuthApi;
import api.EmployeeApi;
import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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
    public void teardown() throws Exception {
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
