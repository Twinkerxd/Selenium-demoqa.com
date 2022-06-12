package MyPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DemoqaCom {
    public static final String URL = "https://demoqa.com/";

    WebDriver driver;

    @BeforeEach
    public void preparingTests() {
        WebDriverManager.chromedriver().setup(); // installing WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    private void findByXpath(String str, boolean click) {
        // TODO we cant use this method at assertions
        if (click) {
            driver.findElement(By.xpath(str)).click();
        } else {
            driver.findElement(By.xpath(str));
        }

    }

    @Test
    public void openingStudentRegistrationForm() throws InterruptedException {
        findByXpath("//h5[text()='Forms']", true);
        findByXpath("//span[text()='Practice Form']", true);

        Thread.sleep(1000);

        Assertions.assertTrue(driver.findElement(By.xpath("//h5[text()='Student Registration Form']")).isDisplayed());
    }

    @AfterEach
    public void closingBrowser() {
        driver.quit();
    }
}
