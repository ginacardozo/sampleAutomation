package sampleTests;

import PagesObjectManager.ObjectManager;
import driverManager.DriverFactory;
import org.testng.annotations.*;

public class BaseTest extends ObjectManager {
    @BeforeTest
    @Parameters({"deviceName","platformVersion","port"})
    public void setUp (String deviceName, String platformVersion, String port){
        System.out.println("\n@before hook - launching driver");
        DriverFactory.configureAppium(deviceName, platformVersion, port);
    }

    @AfterTest
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        DriverFactory.cleanUpDriver();
    }
}
