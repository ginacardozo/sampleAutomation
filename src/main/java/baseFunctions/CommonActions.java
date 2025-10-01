package baseFunctions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
        if (buttonName.equalsIgnoreCase("allow")) {
            waitUntilClickable(driver, allowNotifications);
            allowNotifications.click();
        } else {
            waitUntilClickable(driver, denyNotifications);
            denyNotifications.click();
        }
        System.out.println("\tSelected: " + buttonName);
    }

    public void newLiveSectionHandler (String buttonName) {
        waitUntilClickable(driver, closeNewListenSection);
        closeNewListenSection.click();

        System.out.println("\tSelected: " + buttonName);
    }

    /*Simple Swipe Method*/
    public void swipeScreen(String dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions
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

    public void swipeToHomeSection (String sectionName){
        String sectionXpath = "//android.view.View/preceding-sibling::android.widget.TextView[not(contains(@text,\"View All\"))][not(@text = \"All Videos\")][not(@text = \"Downloads\")][not(@text = \"Favorites\")][not(@text = \"Clips\")]";
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
                swipeScreen("UP");
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
        int xi, xf, y;
        y = section.getLocation().getY()+(section.getSize().getHeight()/2);
        xi = section.getLocation().getX()+(int)(section.getSize().getWidth()*0.9);
        xf = section.getLocation().getX()+(int)(section.getSize().getWidth()*0.1);
        dragAndDrop(xi, y, xf, y, 2,0);
    }
}
