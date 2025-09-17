package sampleTests;

import objectManager.ObjectManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTests extends ObjectManager {
    @BeforeClass
    @Parameters ({"deviceName","platformVersion","port"})
    public void setUp (String deviceName, String platformVersion, String port){
        System.out.println("\n@before hook - launching driver");
        configureAppium(deviceName, platformVersion, port);
    }
    @Test
    public void sampleTest (){
        System.out.println("LOADING GOSPEL STREAM");
        getHomePage().notificationsHandler("Allow");
        getHomePage().newLiveSectionHandler("Close");
        getHomePage().waitForHomeScreenToBeFullyLoaded();

        getRadioPage().radioOpener("24/7 Radio");
        getRadioPage().validateMiniPlayerElements();
        getRadioPage().openRadioPlayer("//*[@content-desc = \"Close\"]/ancestor::android.view.View[2]");
        getRadioPage().minimizeRadioPlayer("Minimize Player");
    }

    @AfterClass
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        cleanUpDriver();
    }
}
