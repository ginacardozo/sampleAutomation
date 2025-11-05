package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;


public class Search extends CommonActions {
    /*Search Screen Elements*/
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"Search\"`]")
    public WebElement searchTabButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Search\"`]")
    public WebElement searchSectionTitle;

    /*Constructor*/
    public Search (IOSDriver driver) {
        super (driver);
    }

    /*Search Screen Functions*/
    public void goToSearchSection(){
        searchTabButton.click();
        waitUntilVisible(driver, searchSectionTitle);
    }
}
