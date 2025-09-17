package objectManager;

import driverManager.DriverFactory;
import pageObjects.*;

public class ObjectManager extends DriverFactory {

    private Home home;
    private Radio radio;

    public Home getHomePage() {
        if (home == null) {
            home = new Home(getDriver());
        }
        return home;
    }

    public Radio getRadioPage() {
        if (radio == null) {
            radio = new Radio(getDriver());
        }
        return radio;
    }
}
