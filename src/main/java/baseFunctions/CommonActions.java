package baseFunctions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Logger;

import static org.testng.Assert.fail;

public class CommonActions {
    protected IOSDriver driver;
    private static final Logger logger= Logger.getLogger(CommonActions.class.getName());

    /*Common Actions Screen Elements*/

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

    public void findElementAndClick(WebElement element){
        element.click();
    }

    public void switchContext (String contextName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> ((IOSDriver) driver).getContextHandles().size() > 1);

        Set<String> contexts = driver.getContextHandles();
        System.out.println("\tAvailable contexts: " + contexts);

        if (contextName.equalsIgnoreCase("native")){
            System.out.println("Switching to Native Context");
            driver.context("NATIVE_APP");

        } else {
            contexts.removeIf(c -> c.equals("NATIVE_APP"));
            for(String context : contexts){
                System.out.println("Switching to context: " + context);
                driver.context(context);

                JavascriptExecutor js = driver;
                String contextTitle = js.executeScript("return document.title;").toString();
                System.out.println("\tEvaluating context: " + contextTitle);

                if (contextTitle.equalsIgnoreCase(contextName)) {
                    System.out.println("Switched to " + contextName + " context successfully.");
                    break;
                } else {
                    fail("Failed to switch to " + contextName + " context.");
                }
            }
        }
    }

    public String codeColor () {
        String verseXpath = "//*[@id = \"p1\"]";
        WebElement verseElement = driver.findElement(By.xpath(verseXpath));
        System.out.println("\tVerse font color: " + verseElement.getCssValue("color"));
        return verseElement.getCssValue("color");
    }

    public void takeScreenshot(String fileName){
        String deviceName = driver.getCapabilities().getCapability("deviceName").toString();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/target/screenshots/" + deviceName + "/";
        File destinationFile = new File(filePath);

        if (!destinationFile.exists() && !destinationFile.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + destinationFile.getAbsolutePath());
        }

        try {
            FileUtils.copyFile(screenshot, new File(filePath + fileName + ".png"));
            System.out.println("Screenshot saved to: " + fileName + ".png");
        } catch (Exception e) {
            logger.info("Failed to save screenshot: " + e.getMessage());
        }
    }

    public void phoneDimensions () {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        System.out.println("Phone Width: " + width + " Phone Height: " + height);
    }
}
