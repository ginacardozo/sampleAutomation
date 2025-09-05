package baseFunctions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class CommonActions {
    protected IOSDriver driver;
    private static final Logger logger= Logger.getLogger(CommonActions.class.getName());

    /*Common Actions Screen Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Allow\"]")
    protected WebElement allowNotifications;
    @AndroidFindBy(xpath = "//*[contains(@resource-id, \"deny_button\")]")
    protected WebElement denyNotifications;


    /*Constructor*/
    public CommonActions (IOSDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    /*Common Actions Screen Functions*/

    public void waitUntilNotVisible (IOSDriver driver, WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(300))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilVisible (IOSDriver driver, WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable (IOSDriver driver, WebElement element){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


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

}
