package driverManager;

public class DriverFactoryCapabilities {
    /*Server Configuration*/
    public static String appiumJS = "/usr/local/lib/node_modules/appium/build/lib/main.js";
    public static String ipAddress = "127.0.0.1";
    public static String url = "http://"+ipAddress+":";

    /*Non Editable Capabilities*/
    public static String appName = "GospelLibrary";
    public static String platformName = "iOS";
    public static String automationName = "XCUITest";
    public static String enableMultiWindows = "enableMultiWindows";
    public static String appPath = System.getProperty("user.dir")+"/app/"+appName+".app";


    /*Editable Capabilities*/
    public static boolean isHeadless = false;


}
