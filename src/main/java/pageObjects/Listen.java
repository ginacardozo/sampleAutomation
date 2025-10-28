package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Listen extends CommonActions {
    /*Listen Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Listen\"]")
    protected WebElement listenSectionIcon;
    @AndroidFindBy(xpath = "//*[@text = \"Podcasts\"]")
    protected WebElement podcastsLabel;

    /*Constructor*/
    public Listen (AndroidDriver driver) {
        super(driver);
    }

    /*Listen Functions*/
    public void navigateToListenSection () {
        waitUntilClickable(driver, listenSectionIcon);
        listenSectionIcon.click();
        waitUntilVisible(driver,podcastsLabel);
        System.out.println("\tListen Section Fully Loaded");
    }
}