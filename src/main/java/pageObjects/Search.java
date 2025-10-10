package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Search extends CommonActions {

    /*Search Elements*/
    @AndroidFindBy(xpath = "//*[@content-desc = \"Search\"]")
    protected WebElement searchSectionIcon;
    @AndroidFindBy(xpath = "//*[@text = \"Videos\"]")
    protected WebElement videosLabel;
    @AndroidFindBy(xpath = "//*[@text = \"Audio\"]")
    protected WebElement audioLabel;
    @AndroidFindBy(xpath = "//*[@content-desc = \"Back\"]")
    protected WebElement backIconSearch;


    public Search (IOSDriver driver) {
        super(driver);
    }

    /*Search Functions*/
    public void navigateToSearchSection() {
        waitUntilClickable(driver, searchSectionIcon);
        searchSectionIcon.click();
        waitUntilVisible(driver,videosLabel);
        waitUntilVisible(driver,audioLabel);
        waitUntilVisible(driver, backIconSearch);
        System.out.println("\tSearch Section Fully Loaded");
        backIconSearch.click();
    }
}
