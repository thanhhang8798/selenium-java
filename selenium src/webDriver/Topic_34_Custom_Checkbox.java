package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_34_Custom_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_CustomRadio() {
        driver.get("https://login.ubuntu.com/");
        By radioButton = By.cssSelector("input#id_new_user");
        By checkbox = By.cssSelector("input#id_accept_tos");

        // dùng javascript để click vào element thẻ input
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(radioButton));
        Assert.assertTrue(driver.findElement(radioButton).isSelected());

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(checkbox));
        Assert.assertTrue(driver.findElement(checkbox).isSelected());
    }

    @Test
    public void TC_02_googleSheet() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(3000);
        By radio = By.cssSelector("div[aria-label='Hà Nội']");
        By checkboxQuangNgai = By.cssSelector("div[aria-label='Quảng Ngãi']");

        driver.findElement(radio).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(radio).getDomAttribute("aria-checked"), "true");

        driver.findElement(checkboxQuangNgai).click();
        Assert.assertEquals(driver.findElement(checkboxQuangNgai).getDomAttribute("aria-checked"), "true");

        // select all checkbox
        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("div[role='checkbox']"));
        for (WebElement checkbox : allCheckbox) {
            if (!checkbox.getDomAttribute("aria-checked").equals("true")) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : allCheckbox) {
            Assert.assertEquals(checkbox.getDomAttribute("aria-checked"), "true");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
