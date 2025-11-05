package PagesObjectManager;

import driverManager.DriverFactory;
import pageObjects.*;

public class ObjectManager extends DriverFactory {
    private Books books;
    private Home home;
    private Search search;


    public Books getBooks() {
        if (books == null) {
            books = new Books(getDriver());
        }
        return books;
    }
    public Home getHome() {
       if (home == null) {
           home = new Home (getDriver());
       }
       return home;
    }
    public Search getSearch() {
        if (search == null) {
            search = new Search (getDriver());
        }
        return search;
    }
}
