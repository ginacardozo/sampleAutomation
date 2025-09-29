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

    public void radioOpener () {
        waitUntilClickable(driver, openMiniRadio);
        System.out.println("\tSelected: " + openMiniRadio.getText());
        openMiniRadio.click();
    }

    public void openRadioPlayer () {
        waitUntilClickable(driver, openRadioPlayer);
        System.out.println("\tSelected: " + openRadioPlayer.getText());
        openRadioPlayer.click();
    }

    public void minimizeRadioPlayer () {
        waitUntilClickable(driver, minimizePlayer);
        System.out.println("\tSelected: " + minimizePlayer.getText());
        minimizePlayer.click();
    }

    public void closeRadioPlayer () {
        waitUntilClickable(driver, closePlayer);
        System.out.println("\tSelected: " + closePlayer.getText());
        closePlayer.click();
    }

}
