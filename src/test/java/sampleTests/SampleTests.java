package sampleTests;

import driverManager.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.Books;
import pageObjects.Home;

public class SampleTests {
    @BeforeClass
    @Parameters ({"deviceName","platformVersion","port"})
    public void setUp (String deviceName, String platformVersion, String port){
        System.out.println("\n@before hook - launching driver");
        DriverFactory.configureAppium(deviceName, platformVersion, port);
    }
    @Test
    public void sampleTest (){
        System.out.println("LOADING GOSPEL LIBRARY HOME SCREEN");
        Home home = new Home(DriverFactory.getDriver());
        Books books = new Books(DriverFactory.getDriver());

        home.waitForHomeScreenToBeFullyLoaded();
        System.out.println("GOSPEL LIBRARY HOME SCREEN LOADED SUCCESSFULLY");
        books.goToBooksSection();
        books.selectBook("Old Testament"); //revisar con chris por que tu quieres mandar un texto y que
        //busque por webelement
        books.selectBookChapter();
    }

    @AfterClass
    public void tearDown (){
        System.out.println("\n@after hook - quit driver");
        DriverFactory.cleanUpDriver();
    }
}
