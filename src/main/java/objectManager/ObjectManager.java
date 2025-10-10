package objectManager;

import driverManager.DriverFactory;
import pageObjects.*;

public class ObjectManager extends DriverFactory {

    private Home home;
    private Radio radio;
    private VideoCollections videoCollections;
    private Listen listen;
    private Settings settings;
    private Search search;
    private Favorites favorites;

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

    public Listen getListenPage() {
        if (listen == null) {
            listen = new Listen(getDriver());
        }
        return listen;
    }

    public Settings getSettingsPage() {
        if (settings == null) {
            settings = new Settings(getDriver());
        }
        return settings;
    }

    public Search getSearchPage() {
        if (search == null) {
            search = new Search(getDriver());
        }
        return search;
    }

    public Favorites getFavoritesPage() {
        if (favorites == null) {
            favorites = new Favorites(getDriver());
        }
        return favorites;
    }
}
