package sampleTests;

import driverManager.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.Home;

public class SampleTests {
    @BeforeClass
    @Parameters ({"deviceName","platformVersion","port"})
    public void setUp (String deviceName, String platformVersion, String port){
        System.out.println("\n@before hook - launching driver");
        DriverFactory.configureAppium(deviceName, platformVersion, port);
    }
    @Test
    public void sampleTest (){
        System.out.println("LOADING GOSPEL STREAM");
        Home home = new Home(DriverFactory.getDriver());

        home.notificationsHandler("Allow");
        home.newLiveSectionHandler("Close");
        home.waitForHomeScreenToBeFullyLoaded();
        home.radioOpener("24/7 Radio");
        home.validateMiniPlayerElements();
        home.openRadioPlayer("//*[@content-desc = \"Close\"]/ancestor::android.view.View[2]");
        home.minimizeRadioPlayer("Minimize Player");
    }

    @AfterClass
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        DriverFactory.cleanUpDriver();
    }
}
