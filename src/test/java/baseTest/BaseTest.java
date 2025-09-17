package baseTest;

import objectManager.ObjectManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest extends ObjectManager {
    @BeforeClass
    @Parameters({"deviceName","platformVersion","port"})
    public void setUp (String deviceName, String platformVersion, String port){
        System.out.println("\n@before hook - launching driver");
        configureAppium(deviceName, platformVersion, port);
    }

    @AfterClass
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        cleanUpDriver();
    }
}
