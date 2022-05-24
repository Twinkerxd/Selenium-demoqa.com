package MyPackage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumLocalDriverTests {

    @Test
    public void openPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\Soft\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
}
