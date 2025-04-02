package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_47_Wait_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Visible() {
        // dk1: có ở UI và có ở html
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_user_shortcut']")).click();

        // chờ cho element hiển thị trong vòng 10s (điều kiện khai báo biến ở beforeClass)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_02_Invisible() {
        // dk2: không có ở UI nhưng vẫn có trong html
        driver.get("https://live.techpanda.org/");
        explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_03_Invisible_notHTML() throws InterruptedException {
        // dk3: khi chuyển trang/ popup => không có trong UI cũng k có ở html
        driver.get("https://tiki.vn/");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div[data-view-id='header_user_shortcut']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("p.login-with-email")).click();

        // vì không tìm thấy element nên sẽ chạy hết thời gian timeout (10s: set lúc khai báo biến)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_04_Present() {
        driver.get("https://live.techpanda.org/");
        // có trong html, dù có ở trên UI hay không
        // dk1: có ở UI và có ở html
        explicitWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("div.footer-container a[title='My Account']")));
        // dk2: không có ở UI nhưng vẫn có trong html
        explicitWait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_05_Staleness() throws InterruptedException {
        // element Ko có trong HTML
        driver.get("https://tiki.vn/");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div[data-view-id='header_user_shortcut']")).click();
        Thread.sleep(3000);

        // phone textbox có trong html
        WebElement phoneTextbox = driver.findElement(By.cssSelector("input[name='tel']"));

        // nhấn vào đăng nhập bằng email => không còn element phone textbox trong html nữa
        driver.findElement(By.cssSelector("p.login-with-email")).click();

        // vì không tìm thấy element nên sẽ chạy hết thời gian timeout (10s: set lúc khai báo biến)
        // hàm stalnessof dùng kiểu webElement nên phải khai báo biến element dạng webElement, không được truyền thẳng trực tiếp find element vào
        explicitWait.until(ExpectedConditions.stalenessOf(phoneTextbox));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
