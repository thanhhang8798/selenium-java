package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_39_Fixed_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_NotInDom() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        // verify popup hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div.auth-form")).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("automationfc");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div.auth-form button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div[role='dialog'] button[type='button']")).click();

        // kiểm tra popup không còn hiển thị
        Assert.assertEquals(driver.findElements(By.cssSelector("div.auth-form")).size(),0);
            // không được dùng assertFalse.isDisplayed để verify
    }

    @Test
    public void TC_02_Fixed_Popup_InDom() {
        driver.get("https://zingpoll.com/");
        driver.findElement(By.cssSelector("a#Loginform")).click();

        // kiểm tra popup hiển thị
        By loginPopup = By.cssSelector("form#loginForm");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // đóng popup
        driver.findElement(By.cssSelector("button[onclick='ResetForm()']")).click();

        // verify popup không còn hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        // verify popup hiển thị
        By loginPopup = By.cssSelector("div[role='dialog']");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("//button[text()='Đăng nhập']")).click();
//        Thread.sleep(3000);
//
//        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]"))
//                .getText(),"Email không được để trống");
//        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]"))
//                .getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();

        // verify popup bị đóng
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
