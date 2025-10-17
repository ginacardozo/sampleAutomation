package sampleTests;

import baseTest.BaseTest;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest {
    @Test
    public void radioPlayer (){
        getRadioPage().radioOpener();
        getRadioPage().validateMiniPlayerElements();
        getRadioPage().openRadioPlayer();
        getRadioPage().minimizeRadioPlayer();
        getRadioPage().closeRadioPlayer();
    }

    @Test
    public void videoPlayer (){
        System.out.println("SWIPING SCREEN");
        getHomePage().swipeToHomeSection("Just Added");
        getHomePage().swipeOverScrollElement("Just Added");
        getVideoCollectionsPage().videoOpener();
        getVideoCollectionsPage().playVideo();
        getVideoCollectionsPage().verifyVideoPlayerIsFullyLoaded();
        getVideoCollectionsPage().minimizeVideoPlayer();
        getVideoCollectionsPage().validateMiniVideoElements();
        getVideoCollectionsPage().compareHoverVideoPlayerSizes();
    }
}
