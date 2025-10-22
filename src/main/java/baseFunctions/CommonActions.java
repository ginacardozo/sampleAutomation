package baseFunctions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;


public class CommonActions implements Waits, Assertions {
    protected IOSDriver driver;
    private static final Logger logger= Logger.getLogger(CommonActions.class.getName());

    /*Common Actions Screen Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Allow\"]")
    protected WebElement allowNotifications;
    @AndroidFindBy(xpath = "//*[contains(@resource-id, \"deny_button\")]")
    protected WebElement denyNotifications;
    @AndroidFindBy(xpath = "//*[@text = \"Close\"]")
    protected WebElement closeNewListenSection;

    /*Constructor*/
    public CommonActions (IOSDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /*Common Actions Screen Functions*/
    public void notificationsHandler (String buttonName) {
        waitUntilVisible(driver, allowNotifications);
        waitUntilVisible(driver, denyNotifications);
        WebElement buttonToClick;

        if (buttonName.equalsIgnoreCase("allow")) {
            buttonToClick = allowNotifications;
        } else {
            buttonToClick = denyNotifications;
        }
        waitUntilClickable(driver, buttonToClick);
        buttonToClick.click();

        System.out.println("\tSelected: " + buttonName);
    }

    public void newLiveSectionHandler (String buttonName) {
        waitUntilClickable(driver, closeNewListenSection);
        closeNewListenSection.click();

        System.out.println("\tSelected: " + buttonName);
    }

    /*Simple Swipe Method*/
    public void swipeScreen(String dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'");
        int xi, xf, yi, yf;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        xi = dims.width / 2;
        yi = dims.height / 2;
        xf = xi;
        yf = yi;

        switch (dir.toUpperCase()) {
            case "DOWN" -> // center of footer
                    yf = (int)(dims.height * 0.9);
            case "UP" -> // center of header
                    yf = (int)(dims.height * 0.1);
            case "LEFT" -> // center of left side
                    xf = (int)(dims.height * 0.1);
            case "RIGHT" -> // center of right side
                    xf = (int)(dims.height * 0.9);
            default -> throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }
        dragAndDrop(xi, yi, xf, yf, 1,1);
    }

    /*Swipe To Find An Element*/
    public CopyOnWriteArrayList <WebElement> getListOfElements (String xpath){
        return new CopyOnWriteArrayList<>(driver.findElements(AppiumBy.xpath(xpath)));
    }

    public void swipeToSection(String sectionName){
        String sectionXpath = "//*[@text = \"View All\"]/preceding-sibling::android.widget.TextView";
        List<WebElement> sections = getListOfElements(sectionXpath);
        ListIterator<WebElement> iterator = sections.listIterator();
        WebElement section;
        String sectionText;

        while (iterator.hasNext()) {
            section = iterator.next();
            sectionText = section.getText();
            System.out.println("Section displayed: " + sectionText);
            if (sectionText.equalsIgnoreCase(sectionName)) {
                System.out.println("Section: " + sectionName + " found");
                // Center the found element on the screen
                int elementY = section.getLocation().getY() + (section.getSize().getHeight());
                int elementX = section.getLocation().getX() + (section.getSize().getWidth() / 2);

                int screenHeight = driver.manage().window().getSize().height;
                int screenCenterY = screenHeight / 2;
                int swipeDistance = elementY - screenCenterY;

                int initialSwipeY = (int)(screenHeight * 0.7);
                int finalSwipeY = (int)(screenHeight * 0.3);

                if (swipeDistance != 0) {
                    dragAndDrop(elementX, initialSwipeY, elementX, finalSwipeY,1, 1);
                    System.out.println("Section " + sectionName + " centered on the screen.");
                }
                break;
            }
            if (section == sections.getLast()) {
                swipeScreen("UP");
                sections = getListOfElements(sectionXpath);
                iterator = sections.listIterator();
                if (sectionText.equalsIgnoreCase(sections.getLast().getText())) {
                    Assert.fail("Element with text: " + sectionName + " not found after several swipes");
                }
            }
        }
    }

    /*Drag and Drop on screen Method*/
    public void dragAndDrop(int xi, int yi, int xf, int yf, int duration, int pauseTime) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 1);

        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), xi, yi));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofSeconds(duration), PointerInput.Origin.viewport(), xf, yf));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(new Pause(finger, Duration.ofSeconds(pauseTime)));

        driver.perform(java.util.Collections.singletonList(dragAndDrop));
    }

    public void swipeOverScrollElement(String sectionName){
        String sectionXpath = "//android.widget.TextView[@content-desc = \"View All "+sectionName+"\"]/following-sibling::android.view.View[1]";
        WebElement section = driver.findElement(AppiumBy.xpath(sectionXpath));

        int y = section.getLocation().getY() + (section.getSize().getHeight() / 2);
        int xi = section.getLocation().getX() + (int)(section.getSize().getWidth()*(0.9));
        int xf = section.getLocation().getX() + (int)(section.getSize().getWidth()*(0.1));
        dragAndDrop(xi, y, xf, y, 2, 0);
    }

    /*Double Tap on screen Method*/
    public void doubleTap(int xi, int yi) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence doubleTap = new Sequence(finger, 1);

        doubleTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), xi, yi));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(java.util.Collections.singletonList(doubleTap));
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
}
