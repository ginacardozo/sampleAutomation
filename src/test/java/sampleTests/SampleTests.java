package sampleTests;

import baseTest.BaseTest;
import org.testng.annotations.Test;

public class SampleTests extends BaseTest {
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
}