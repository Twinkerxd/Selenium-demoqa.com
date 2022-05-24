package MyPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriverManagerTests {
    private static final String URL = "https://the-internet.herokuapp.com/";

    WebDriver driver;

    @BeforeEach
    public void preparingTests() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    @DisplayName("Проверяем урл страницы")
    public void checkUrl() {
        Assertions.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверяем заголовок страницы")
    public void checkTitle() {
        Assertions.assertEquals("The Internet", driver.getTitle());
    }

    @AfterEach
    public void closingBrowser() {
        driver.quit();
    }
}
