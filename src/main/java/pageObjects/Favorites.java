package pageObjects;

import baseFunctions.CommonActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class Favorites extends CommonActions {
    /*Favorites Elements*/
    @AndroidFindBy(xpath = "//*[@text = \"Favorites\"]")
    protected WebElement favoritesSectionIcon;
    @AndroidFindBy(xpath = "//*[@text = \"No Favorite Videos\"]")
    protected WebElement noFavoritesVideosLabel;

    /*Constructor*/
    public Favorites(IOSDriver driver) {
        super(driver);
    }

    /*Favorites Functions*/
    public void navigateToFavoritesSection() {
        waitUntilClickable(driver, favoritesSectionIcon);
        favoritesSectionIcon.click();
        waitUntilVisible(driver, noFavoritesVideosLabel);
        System.out.println("\tFavorites Section Fully Loaded");
    }
}
