package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

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
}
