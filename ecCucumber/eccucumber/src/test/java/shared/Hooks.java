package shared;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private TestContext context;
    
    public Hooks(TestContext context){
        this.context = context;
    }
    
    @Before 
    public void setup(){
        context.startDriver();
    }

    @After 
    public void teardown(){
       context.quitDriver();
    }

}
