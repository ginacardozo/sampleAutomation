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

        getRadioPage().radioOpener();
        getRadioPage().validateMiniPlayerElements();
        getRadioPage().openRadioPlayer();
        getRadioPage().minimizeRadioPlayer();
        getRadioPage().closeRadioPlayer();

        System.out.println("SWIPING SCREEN");

        getHomePage().swipeToHomeSection("Music");
    }
}