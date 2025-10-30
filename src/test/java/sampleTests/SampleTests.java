package sampleTests;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class SampleTests extends BaseTest {
    @Test
    public void sampleTest (){
        System.out.println("LOADING GOSPEL LIBRARY HOME SCREEN");
        loadHomeScreen();
        loadBooksScreen();
        loadBookChapter();
        webViewSteps();
    }

    @Step("Home is fully loaded")
    public void loadHomeScreen(){
        getHome().waitForHomeScreenToBeFullyLoaded();
        System.out.println("GOSPEL LIBRARY HOME SCREEN LOADED SUCCESSFULLY");
    }

    @Step("Books is fully loaded")
    public void loadBooksScreen() {
        getBooks().goToBooksSection();
        System.out.println("GOSPEL LIBRARY BOOKS SCREEN LOADED SUCCESSFULLY");
    }

    @Step ("Chapter is fully loaded")
    public void loadBookChapter() {
        getBooks().selectBook("Old Testament");
        getBooks().selectBookChapter();
        System.out.println("Book chapter loaded successfully");
    }

    @Step("WebView steps related")
    public void webViewSteps() {
        getBooks().validateWebviewProperty("Genesis 1");
        getBooks().selectMoreOptions();
        getBooks().codeColor();
        getBooks().validateWebviewProperty("NATIVE");
        getHome().phoneDimensions();
        getBooks().takeScreenshot("SampleTest_Screenshot");
        System.out.println("WebView steps executed successfully");
    }
}
