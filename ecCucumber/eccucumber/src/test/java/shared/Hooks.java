package shared;


import api.EmployeeApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private TestContext context;
    private EmployeeApi employeeApi;
    
    public Hooks(TestContext context, EmployeeApi employeeApi){
        this.context = context;
        this.employeeApi = employeeApi;
    }
    
    @Before 
    public void setup(){
        context.startDriver();
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
