package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_51_Wait_Fluent {
    WebDriver driver;

    // khai báo
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        // config fluent wait: trong vòng 5s, cứ mỗi 1/4s sẽ tìm chữ Hello world hiển thị
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);

        // wait hiển thị text hello world
        String helloText = fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                System.out.println("-------Đang tìm element---------");
                return driver.findElement(By.cssSelector("div#finish>h4")).getText();
            }
        });
        // verify text
            // cách 1: nên dùng vì so sánh được thực tế với mong đợi
        Assert.assertEquals(helloText,"Hello World!");

            // cách 2: dùng assertTrue => không nên dùng vì chỉ biết là true/ fale chứ không rõ text là gì
        boolean helloStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
            }
        });
        Assert.assertTrue(helloStatus);

    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        // đếm ngược từ giay 12 về 00
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(3500))
                .ignoring(NoSuchElementException.class);

        // viết gộp với hàm assert, tương tự TC 1 kiểu boolean
        Assert.assertTrue(fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String countTime = driver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
                System.out.println(countTime);
                return countTime.equals("01:01:00");
            }
        }));
    }

    // viết hàm: tìm element với polling time là 1s kiểm tra 1 lần
    public WebElement findElement(By by) {
        // khai báo + khởi tạo
        FluentWait fluentWait1 = new FluentWait(driver);

        // config timeout/ polling/ exception
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(java.util.NoSuchElementException.class);

        // điều kiện
        return (WebElement) fluentWait1.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    // hàm: kiểm tra element hiển thị: isDisplayed
    public boolean isElementDisplayed(By by) {
        // khai báo + khởi tạo
        FluentWait fluentWait2 = new FluentWait(driver);

        // config timeout/ polling/ exception
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(java.util.NoSuchElementException.class);

        // verify
        return (boolean) fluentWait2.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement drive) {
                return drive.findElement(by).isDisplayed();
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
