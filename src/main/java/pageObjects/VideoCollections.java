package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class VideoCollections extends CommonActions {

    /*Video Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Messy Room Meltdown\"]")
    protected WebElement openVideo;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Play video\"]")
    protected WebElement playVideo;
    @AndroidFindBy(className = "android.widget.ImageButton")
    protected WebElement minimizeVideo;

    @AndroidFindBy(xpath= "/hierarchy/android.widget.FrameLayout[2]")
    protected WebElement firstMinimizedVideo;
    @AndroidFindBy(xpath = "//*[contains(@resource-id,\"exo_progress\")]")
    protected WebElement playingTime;

    @AndroidFindBy(xpath = "//*[@content-desc = \"Close\"]")
    protected WebElement closeVideo;


    public VideoCollections(IOSDriver driver) {
        super(driver);
    }

    public void videoOpener() {
        waitUntilClickable(driver, openVideo);
        System.out.println("\tSelected: " + openVideo.getText());
        openVideo.click();
    }

    public void playVideo() {
        waitUntilClickable(driver, playVideo);
        System.out.println("\tSelected: " + playVideo.getText());
        playVideo.click();
    }

    public void verifyVideoPlayerIsFullyLoaded() {
        waitUntilAttributeValueToBe(driver, playingTime, "00:02");
        System.out.println("\tVideo Player Fully Loaded");
    }

    public void minimizeVideoPlayer() {
        waitUntilClickable(driver, minimizeVideo);
        minimizeVideo.click();
        System.out.println("Video minimized");
    }
    public void validateMiniVideoElements() {
        assertElementIsVisible(firstMinimizedVideo);
        System.out.println("Minimized video found");
    }

    public void doubleTapMinimizedVideo() {
        int xi, yi;
        xi = firstMinimizedVideo.getLocation().getX();
        yi = firstMinimizedVideo.getLocation().getY();
        doubleTap(xi, yi);
        System.out.println("Double tapped on minimized video");
    }

    public void compareHoverVideoPlayerSizes(){
        Dimension hoverSizeOne = firstMinimizedVideo.getSize();
        doubleTapMinimizedVideo();
        Dimension hoverSizeTwo = firstMinimizedVideo.getSize();

        while (hoverSizeOne.height==(hoverSizeTwo.height) && hoverSizeOne.width==(hoverSizeTwo.width)) {
            hoverSizeTwo = firstMinimizedVideo.getSize();
        }
        System.out.println("The minimized video is smaller than the double tapped video.");
        firstMinimizedVideo.click();
        closeVideo.click();
    }
}
