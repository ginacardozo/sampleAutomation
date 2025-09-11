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

}
