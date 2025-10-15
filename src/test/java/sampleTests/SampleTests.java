package sampleTests;

import baseTest.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTests extends BaseTest {

    @Test
    public void sampleTest2 () {
        // Navigate to Listen Section
        getListenPage().navigateToListenSection();
    }

    @Test
    public void sampleTest3 () {
        // Navigate to Favorites Section
        getFavoritesPage().navigateToFavoritesSection();
    }

    @Test
    public void sampleTest4 () {
        // Navigate to Search Section
        getSearchPage().navigateToSearchSection();
    }

   @Test
    public void sampleTest5 () {
        // Navigate to Settings Section
        getSettingsPage().navigateToSettingsSection();
    }
}