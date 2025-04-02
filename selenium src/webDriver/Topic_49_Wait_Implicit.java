package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_49_Wait_Implicit {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_0s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        // click vào button start
        driver.findElement(By.cssSelector("https://automationfc.github.io/dynamic-loading/")).click();
        // loading 5s hiển thị Hello world
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")), "Hello World!");

        // kết quả: fail ở dòng assert, time chạy TC hết < 0.5s
    }

    @Test
    public void TC_02_3s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("https://automationfc.github.io/dynamic-loading/")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")), "Hello World!");
        // kết quả: fail ở dòng assert, time chạy TC hết 3s
    }

    @Test
    public void TC_03_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("https://automationfc.github.io/dynamic-loading/")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")), "Hello World!");
        // kết quả: pass, time chạy TC hết 5s
    }

    @Test
    public void TC_04_30s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.cssSelector("https://automationfc.github.io/dynamic-loading/")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")), "Hello World!");
        // kết quả: pass, time chạy TC hết 5s
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
