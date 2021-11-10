import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    public void FirstTest() {
        System.out.println("Welcome to Java Selenium Training Class");
    }

    @Test
    public void FirstSeleniumTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com/");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }
}
