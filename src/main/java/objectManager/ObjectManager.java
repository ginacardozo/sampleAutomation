package objectManager;

import driverManager.DriverFactory;
import pageObjects.*;

public class ObjectManager extends DriverFactory {

    private Home home;
    private Radio radio;
    private VideoCollections videoCollections;

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

    public VideoCollections getVideoCollectionsPage() {
        if (videoCollections == null) {
            videoCollections = new VideoCollections(getDriver());
        }
        return videoCollections;
    }
}
