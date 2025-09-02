package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class Home extends CommonActions {
    /*Home Screen Elements*/
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Loading…\"`]")
    protected WebElement loadingIcon;

    /*Constructor*/
    public Home (IOSDriver driver) {
        super (driver); /*usa el driver de commonactions*/

    }

    /*Home Screen Functions*/

    public void waitForHomeScreenToBeFullyLoaded (){
        waitUntilNotVisible(driver, loadingIcon);
        
    }
}
