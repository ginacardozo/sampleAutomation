package baseFunctions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class CommonActions implements Waits, Assertions {
    protected IOSDriver driver;
    private static final Logger logger= Logger.getLogger(CommonActions.class.getName());

    /*Common Actions Screen Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Allow\"]")
    protected WebElement allowNotifications;
    @AndroidFindBy(xpath = "//*[contains(@resource-id, \"deny_button\")]")
    protected WebElement denyNotifications;
    @AndroidFindBy(xpath = "//*[@text = \"Close\"]")
    protected WebElement closeNewListenSection;

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


    /*Constructor*/
    public CommonActions (IOSDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /*Common Actions Screen Functions*/
    public void notificationsHandler (String buttonName) {
        if (buttonName.equalsIgnoreCase("allow")) {
            waitUntilClickable(driver, allowNotifications);
            allowNotifications.click();
        } else {
            waitUntilClickable(driver, denyNotifications);
            denyNotifications.click();
        }
        System.out.println("\tSelected: " + buttonName);
    }

    public void newLiveSectionHandler (String buttonName) {
        waitUntilClickable(driver, closeNewListenSection);
        closeNewListenSection.click();

        System.out.println("\tSelected: " + buttonName);
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
