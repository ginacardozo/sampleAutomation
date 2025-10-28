package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Home extends CommonActions {
    /*Home Screen Elements*/
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Watch\"]")
    protected WebElement watchIcon;

    /*Constructor*/
    public Home (AndroidDriver driver) {
        super (driver); /*usa el driver de common actions*/
    }

    /*Home Screen Functions*/
    public void waitForHomeScreenToBeFullyLoaded (){
        waitUntilVisible(driver, watchIcon);
        System.out.println("\tHome Screen Fully Loaded");
    }
}