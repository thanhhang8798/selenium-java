package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Multiple_Browser {
    WebDriver driver;

    // thêm annotation: khi có 2 tham số thì để dạng mảng {}
    @Parameters("browser")

    @BeforeClass
    public void beforeClass(String browserName) {
        System.out.println("Browser name = " + browserName);
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw  new RuntimeException("Browser name is not support");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Custom() {
        driver.get("https://login.ubuntu.com/");
        By radioButton = By.cssSelector("input#id_new_user");
        By checkbox = By.cssSelector("input#id_accept_tos");

        // dùng javascript để click vào element thẻ input
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(radioButton));
        Assert.assertTrue(driver.findElement(radioButton).isSelected());

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(checkbox));
        Assert.assertTrue(driver.findElement(checkbox).isSelected());
    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
