package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_32_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // Ckickable
        // chờ cho 1 element không được phép click trong vòng 10s
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .not(ExpectedConditions.elementToBeClickable(loginButton)));

        // Disable/ enable button
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        // Text button hiển thị đúng
        Assert.assertEquals(driver.findElement(loginButton).getText().trim(), "Đăng nhập");

        // Check màu background button - viết rõ code
        String backgroundRegister = driver.findElement(loginButton).getCssValue("background-color");
        Assert.assertEquals(backgroundRegister, "rgba(0, 0, 0, 0)");
            // convert dạng rgb/rgba sang dạng hexa
        Assert.assertEquals(Color.fromString(backgroundRegister).asHex().toUpperCase(), "#000000");

        // nhập email, mật khẩu hợp leej
        driver.findElement(By.id("login_username")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("abcABC");

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        // Check màu background button - viết gộp code
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color"))
                .asHex().toUpperCase(), "#C92127");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginButton));

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
