package DemoQAPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    public static final String ELEMENTS = "Elements";
    public static final String FORMS = "Forms";
    public static final String ALERTS = "Alerts, Frame & Windows";
    public static final String WIDGETS = "Widgets";
    public static final String INTERACTIONS = "Interactions";
    public static final String BOOK = "Book Store Application";


    @FindBy(css = ".card")
    private List<WebElement> cards;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement findElement(String name) {
        WebElement element = null;

        for (WebElement card : cards) {
            if (card.getText().equals(name)) {
                element = card;
            }
        }

        return element;
    }
}
