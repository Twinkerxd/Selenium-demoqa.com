package DemoQAPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    @FindBy(css = ".card")
    private List<WebElement> cards;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement findElement(String name) {
        WebElement x = null;

        for (WebElement card : cards) {
            if (card.getText().equals(name)) {
                x = card;
            }
        }

        return x;
    }
}
