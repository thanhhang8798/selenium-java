package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_50_Wait_Explicit_II {
    WebDriver driver;
    WebDriverWait explicitWait;

    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloText = By.cssSelector("div#finish>h4");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_0s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(0));

        // click vào button start
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton)).click();

        // loading 5s hiển thị Hello world
        // Cách 1: chờ cho loading icon biên mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // cách 2: chờ cho đến khi text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");

        // kết quả: fail ở dòng assert, time chạy TC hết < 0.5s
    }

    @Test
    public void TC_02_3s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // click vào button start
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton)).click();

        // loading 5s hiển thị Hello world
        // Cách 1: chờ cho loading icon biên mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // cách 2: chờ cho đến khi text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
        // kết quả: fail ở dòng assert, time chạy TC hết 3s
    }

    @Test
    public void TC_03_5s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // click vào button start
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton)).click();

        // loading 5s hiển thị Hello world
        // Cách 1: chờ cho loading icon biên mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // cách 2: chờ cho đến khi text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
        // kết quả: pass, time chạy TC hết 5s
    }

    @Test
    public void TC_04_10s() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // click vào button start
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton)).click();

        // loading 5s hiển thị Hello world
        // Cách 1: chờ cho loading icon biên mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        // cách 2: chờ cho đến khi text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
        // kết quả: pass, time chạy TC hết 5s
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
