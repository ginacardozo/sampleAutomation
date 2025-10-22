package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Settings extends CommonActions {

    /*Settings Elements*/
    @AndroidFindBy(xpath = "//*[@content-desc = \"Settings\"]")
    protected WebElement settingsSectionIcon;
    @AndroidFindBy(xpath = "//*[@text = \"About\"]")
    protected WebElement aboutLabel;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Back\"]")
    protected WebElement backIconSettings;

    /*Constructor*/
    public Settings(IOSDriver driver) {
        super(driver);
    }

    /*Setting Functions*/
    public void navigateToSettingsSection() {
        waitUntilClickable(driver, settingsSectionIcon);
        settingsSectionIcon.click();
        waitUntilVisible(driver, aboutLabel);
        waitUntilVisible(driver, backIconSettings);
        System.out.println("\tSettings Section Fully Loaded");
        backIconSettings.click();
    }
}
