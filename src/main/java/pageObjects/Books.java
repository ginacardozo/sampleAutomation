package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Books extends CommonActions {
    /*Books Screen Elements*/
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"books.vertical\"`]")
    public WebElement bookTabButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Scriptures\"`]")
    public WebElement scripturesSectionTitle;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Old Testament\"`]")
    public WebElement oldTestamentOption;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Genesis\"`]")
    public WebElement genesisBookOption;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"1\"`]")
    public WebElement chapterOneOption;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"CHAPTER 1\"`]")
    public WebElement chapterOneTitle;
    @iOSXCUITFindBy (iOSClassChain = "**/XCUIElementTypeOther[`name == \"More\"`]")
    public WebElement moreOptionsButton;
    @iOSXCUITFindBy (iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Show Footnotes\"`]")
    public WebElement footNotesText;

    /*Constructor*/
    public Books (IOSDriver driver) {
        super (driver);
    }

    /*Books Screen Functions*/
    public void goToBooksSection (){
        bookTabButton.click();
        waitUntilVisible(driver, scripturesSectionTitle);
    }

    public void selectBook(String scriptureName){
        scripturesSectionTitle.click();
        findElementAndClick(oldTestamentOption);
    }

    public void selectBookChapter(){
        findElementAndClick(genesisBookOption);
        findElementAndClick(chapterOneOption);
        waitUntilVisible(driver, chapterOneTitle);
    }

    public void validateWebviewProperty(String contextName) {
        switchContext(contextName);
    }

    public void selectMoreOptions(String optionName){
        findElementAndClick(moreOptionsButton);
        readingOptions(optionName);
    }

    public void assertDisplayOptions(){
        waitUntilVisible(driver, footNotesText);
        System.out.println("Display Options section is visible");
    }

    public void readingOptions(String optionName){
        String menuXpath = "//XCUIElementTypeCell/XCUIElementTypeButton";
        List<WebElement> options = getElementsByXpath(menuXpath);
        String optionValue;
        for (WebElement option : options) {
            optionValue = option.getAttribute("label");
            if (optionValue.equalsIgnoreCase(optionName)) {
                findElementAndClick(option);
                System.out.println(optionName + " option selected");
                break;
            }
        }
    }
}
