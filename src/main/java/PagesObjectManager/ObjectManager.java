package PagesObjectManager;

import driverManager.DriverFactory;
import pageObjects.*;

public class ObjectManager extends DriverFactory {
    private Books books;
    private Home home;


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
}
