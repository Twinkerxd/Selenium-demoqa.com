package MyPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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
    @DisplayName("Checking current URL")
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
    public void successfulLoginCheck() {
        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(LOGIN);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("radius")).click();

        String successfulLoginMessage = driver.findElement(By.id("flash")).getText();
        Assertions.assertTrue(successfulLoginMessage.contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Checking successful logout")
    public void successfulLogoutCheck() {
        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(LOGIN);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("radius")).click();
        driver.findElement(By.xpath("//i[@class='icon-2x icon-signout']")).click();

        String successfulLogoutMessage = driver.findElement(By.id("flash")).getText();
        Assertions.assertTrue(successfulLogoutMessage.contains("You logged out of the secure area!"));
    }

    @Test
    @DisplayName("Checking adding elements")
    public void addingElements() {
        driver.findElement(By.xpath("//a[text()='Add/Remove Elements']")).click();
        // need to check that we dont have elements before adding a new one
        Assertions.assertTrue(driver.findElements(By.className("added-manually")).isEmpty());

        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        Assertions.assertEquals(1, driver.findElements(By.className("added-manually")).size());

    }

    @Test
    @DisplayName("Checking deleting elements")
    public void deletingElements() {
        driver.findElement(By.xpath("//a[text()='Add/Remove Elements']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();

        // deleting one element
        driver.findElement(By.className("added-manually")).click();

        Assertions.assertEquals(1, driver.findElements(By.className("added-manually")).size());
    }

    @Test
    @DisplayName("Checking checkboxes")
    public void selectingCheckboxes() {
        driver.findElement(By.xpath("//a[text()='Checkboxes']")).click();
        String checkboxPath = "//input[@type='checkbox']";
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxPath));

        Assertions.assertFalse(checkboxes.get(0).isSelected());
        Assertions.assertTrue(checkboxes.get(1).isSelected());

        driver.findElement(By.xpath(checkboxPath)).click();
        Assertions.assertTrue(checkboxes.get(0).isSelected());
        Assertions.assertTrue(checkboxes.get(1).isSelected());

        driver.findElement(By.xpath(checkboxPath)).click();
        Assertions.assertFalse(checkboxes.get(0).isSelected());
        Assertions.assertTrue(checkboxes.get(1).isSelected());
    }

    @AfterEach
    public void closingBrowser() {
        driver.quit();
    }
}
