package baseTest;

import driverManager.DriverFactoryCapabilities;
import objectManager.ObjectManager;
import org.testng.annotations.*;

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

    @BeforeMethod
    public void popUpTest (){
        System.out.println("LOADING GOSPEL STREAM");
        getHomePage().notificationsHandler("Allow");
        getHomePage().newLiveSectionHandler("Close");
        getHomePage().waitForHomeScreenToBeFullyLoaded();
    }

    @AfterMethod
    public void clearTest() {
        getDriver().terminateApp("org.lds.stream.alpha");
        getDriver().removeApp("org.lds.stream.alpha");
        getDriver().installApp(DriverFactoryCapabilities.appPath);
        getDriver().activateApp("org.lds.stream.alpha");
    }
}
