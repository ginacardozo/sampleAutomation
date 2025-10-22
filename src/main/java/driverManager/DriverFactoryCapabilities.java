package driverManager;

public class DriverFactoryCapabilities {
    /*Server Configuration*/
    public static String appiumJS = "/usr/local/lib/node_modules/appium/build/lib/main.js";
    public static String ipAddress = "127.0.0.1";
    public static String url = "http://"+ipAddress+":";

    /*Non Editable Capabilities*/
    public static String appName = "app-alpha";
    public static String platformName = "Android";
    public static String automationName = "UIAutomator2";
    public static String enableMultiWindows = "enableMultiWindows";
    public static String appPath = System.getProperty("user.dir")+"/app/"+appName+".apk";

    /*Editable Capabilities*/
    public static boolean isHeadless = false;
}
