package MyPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriverManagerTests {
    private static final String URL = "https://the-internet.herokuapp.com/";
    private static final String LOGIN = "tomsmith";
    private static final String PASSWORD = "SuperSecretPassword!";

    WebDriver driver;

    @BeforeEach
    public void preparingTests() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    @DisplayName("URL checking")
    public void urlCheck() {
        Assertions.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checking title of the page")
    public void titleCheck() {
        Assertions.assertEquals("The Internet", driver.getTitle());
    }

    @Test
    @DisplayName("Checking successful login message")
    public void loginCheck() {
        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(LOGIN);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("radius")).click();

        String successfulLoginMessage = driver.findElement(By.id("flash")).getText();
        Assertions.assertTrue(successfulLoginMessage.contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Checking successful logout")
    public void logoutCheck() {
        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(LOGIN);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("radius")).click();
        driver.findElement(By.xpath("//i[@class='icon-2x icon-signout']")).click();

        String successfulLogoutMessage = driver.findElement(By.id("flash")).getText();
        Assertions.assertTrue(successfulLogoutMessage.contains("You logged out of the secure area!"));
    }

    @AfterEach
    public void closingBrowser() {
        driver.quit();
    }
}
