package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_49_Wait_Static {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_0s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        // click vào button start
        driver.findElement(By.cssSelector("div#start>button")).click();
        // loading 5s hiển thị Hello world

        // sử dụng hàm
        sleepInSecond(0);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

        // kết quả: fail ở dòng assert, time chạy TC hết < 0.5s
    }

    @Test
    public void TC_02_3s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // kết quả: fail ở dòng assert, time chạy TC hết 3s
    }

    @Test
    public void TC_03_5s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // kết quả: pass, time chạy TC hết 5s
    }

    @Test
    public void TC_04_10s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(10);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // kết quả: pass, time chạy TC hết 10s
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
