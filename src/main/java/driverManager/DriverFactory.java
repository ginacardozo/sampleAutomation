package driverManager;

import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

import static driverManager.DriverFactoryCapabilities.*;

public class DriverFactory {
    private static final ThreadLocal <IOSDriver> mobileDriver = new ThreadLocal<>();
    private static final ThreadLocal <Integer> servicePort = new ThreadLocal<>();
    private static final Duration implicitWait = Duration.ofSeconds(30);
    private static final Duration wdaTimeLaunchTimeOut = Duration.ofSeconds(60);
    private static final Logger logger = Logger.getLogger(DriverFactory.class.getName());

    public static void configureAppium(String deviceName, String platformVersion, String port){

        int currentPort = Integer.parseInt(port);
        servicePort.set(currentPort);

        AppiumServiceManager.startAppiumService(appiumJS, ipAddress, currentPort);
        try{
            logger.info("Application Directory: "+ System.getProperty("user.dir"));
            XCUITestOptions mobileCapabilities = new XCUITestOptions();

            mobileCapabilities.setPlatformName(platformName);
            mobileCapabilities.setPlatformVersion(platformVersion);
            mobileCapabilities.setAutomationName(automationName);
            mobileCapabilities.setDeviceName(deviceName);
            mobileCapabilities.setApp(appPath);
            mobileCapabilities.setCapability(enableMultiWindows, true);
            mobileCapabilities.setWdaLaunchTimeout(wdaTimeLaunchTimeOut);
            mobileCapabilities.setCapability("isHeadless", isHeadless);

            mobileCapabilities.setWdaLocalPort(8100+(currentPort-4723));
            mobileCapabilities.setCapability("wda.connectionTimeout", 60000);
            mobileCapabilities.setCapability("wda.startupRetries", 2);
            mobileCapabilities.setCapability("wda.startupRetryInterval", 20000);

            URI uri = new URI(url+currentPort);
            URL fixedURL = uri.toURL();
            IOSDriver driver = new IOSDriver(fixedURL, mobileCapabilities);

            driver.manage().timeouts().implicitlyWait(implicitWait);
            driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 60);
            mobileDriver.set(driver);

        } catch (MalformedURLException | URISyntaxException exception){
            throw new RuntimeException(exception);
        }

    }


    public static IOSDriver getDriver () {
        return mobileDriver.get();
    }

    public static void cleanUpDriver (){
        try{
            getDriver();
            getDriver().quit();
            mobileDriver.remove();
        } finally {
            AppiumServiceManager.stopAppiumService();
            servicePort.remove();
        }
    }
}
