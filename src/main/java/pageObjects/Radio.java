package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Radio extends CommonActions {

    /*Radio Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"24/7 Radio\"]")
    protected WebElement openMiniRadio;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Close\"]/ancestor::android.view.View[2]")
    protected WebElement openRadioPlayer;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Minimize Player\"]")
    protected WebElement minimizePlayer;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Pause\"]")
    protected WebElement pausePlayer;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Close\"]")
    protected WebElement closePlayer;

    public Radio(IOSDriver driver) {
        super(driver);
    }


    public void validateMiniPlayerElements (){
        assertElementIsVisible(pausePlayer);
        assertElementIsVisible(closePlayer);
    }

    public void radioOpener (String radioName) {
        waitUntilClickable(driver, openMiniRadio);
        openMiniRadio.click();

        System.out.println("\tSelected: " + radioName);
    }

    public void openRadioPlayer (String miniRadio) {
        waitUntilClickable(driver, openRadioPlayer);
        openRadioPlayer.click();

        System.out.println("\tSelected: " + miniRadio);
    }

    public void minimizeRadioPlayer (String radioPlayer) {
        waitUntilClickable(driver, minimizePlayer);
        minimizePlayer.click();

        System.out.println("\tSelected: " + radioPlayer);
    }

}
