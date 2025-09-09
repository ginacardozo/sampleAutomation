package baseFunctions;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public interface Assertions {

    default void assertElementIsVisible(WebElement element) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(element.isDisplayed(), "Element not displayed");

        String elementValue = element.getAttribute("text");
        if (elementValue.isBlank()) {
            elementValue = element.getAttribute("resource-id");
        } else if (elementValue.isBlank()) {
            elementValue = element.getAttribute("content-desk");
        } else {
            elementValue = "element";
        }
        System.out.println("\t" + elementValue + " is present as expected");
    }
}
