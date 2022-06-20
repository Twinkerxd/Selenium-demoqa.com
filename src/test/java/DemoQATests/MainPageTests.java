package DemoQATests;

import DemoQAPages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static DemoQATests.Constants.*;


public class MainPageTests extends BaseTests {
    private MainPage mainPage;

    @Test
    public void elementsOnTheMainPage() {
        mainPage = new MainPage(driver);

        Assertions.assertTrue(mainPage.findElement(ELEMENTS).isDisplayed());
        Assertions.assertTrue(mainPage.findElement(FORMS).isDisplayed());
        Assertions.assertTrue(mainPage.findElement(ALERTS).isDisplayed());
        Assertions.assertTrue(mainPage.findElement(WIDGETS).isDisplayed());
        Assertions.assertTrue(mainPage.findElement(INTERACTIONS).isDisplayed());
        Assertions.assertTrue(mainPage.findElement(BOOK).isDisplayed());
    }
}
