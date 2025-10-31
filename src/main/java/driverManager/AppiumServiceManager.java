package driverManager;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumServiceManager {

    private static final ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    public static void startAppiumService(String appiumJs, String ipAddress, int port) {
        service.set(new AppiumServiceBuilder()
                        .withArgument(()->"--allow-insecure", "adb_shell")
                .withAppiumJS(new File(appiumJs))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build());
        getAppiumService().start();
    }

    public static AppiumDriverLocalService getAppiumService() {
        return service.get();
    }

    public static void stopAppiumService() {
        if (getAppiumService() != null && getAppiumService().isRunning()) {
            getAppiumService().stop();
            service.remove();
        }
    }
}
