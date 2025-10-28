package baseTest;

import driverManager.DriverFactoryCapabilities;
import objectManager.ObjectManager;
import org.testng.annotations.*;

public class BaseTest extends ObjectManager {
    @BeforeTest
    @Parameters({"deviceName","platformVersion","port", "udid"})
    public void setUp (String deviceName, String platformVersion, String port, String udid){
        System.out.println("\n@before hook - launching driver");
        configureAppium(deviceName, platformVersion, port, udid);
    }

    @AfterTest
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        cleanUpDriver();
    }

   @BeforeMethod
    public void popUpTest (){
        System.out.println("LOADING GOSPEL STREAM");
        getHomePage().popUpHandler("New Listen Section", "Close");
        getHomePage().waitForHomeScreenToBeFullyLoaded();
    }

    @AfterMethod
    public void clearTest() {
        getDriver().terminateApp("org.lds.stream.alpha");
        getDriver().removeApp("org.lds.stream.alpha");
        getDriver().installApp(DriverFactoryCapabilities.appPath);
        getDriver().activateApp("org.lds.stream.alpha");
        //grantPermission(getDriver(), "android.permission.ACCESS_FINE_LOCATION");
    }
}
