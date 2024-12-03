package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_28_BaiTap_Textarea {
    private static final Logger log = LoggerFactory.getLogger(Topic_28_BaiTap_Textarea.class);
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_BaiTapTC02() throws InterruptedException {
    // làm TC 02 của topic 07: https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit?tab=t.0#heading=h.fusc17j2zjta
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // khai báo biến kiểu by (không dùng kiểu webElement vì trang khác nhau)
        By userName = By.name("username");
        By password = By.name("password");
        By loginButton = By.cssSelector("button.orangehrm-login-button");
        driver.findElement(userName).sendKeys("Admin");
        driver.findElement(password).sendKeys("admin123");
        driver.findElement(loginButton).click();

            // khi manual test thấy có icon loading => dùng hàm sleep
        Thread.sleep(5000); // nhấn add exception to method

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(3000);

        // add employedd
        String firstName = "Thanh";
        String lastName = "Hang";
        String employeeId = driver.findElement(By.cssSelector("form[class='oxd-form'] input[class='oxd-input oxd-input--active']"))
                .getAttribute("value"); // dùng DOM/ properties để xem value

        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("div.oxd-switch-wrapper")).click();

        // khai báo biến tự động username
        String user = "thanhhang" + new Random().nextInt(9999);
        String pwUser = "abc123@";
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
                .sendKeys(user);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
                .sendKeys(pwUser);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
                .sendKeys(pwUser);
        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
        Thread.sleep(7000);

        // verify dữ liệu đăng ký employee
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value"),employeeId);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

        // add immigration
        String number = "0348397112";
        String comments = "test add comments\n" +
                "123456";
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys(comments);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("i[class='oxd-icon bi-pencil-fill']")).click();
        Thread.sleep(3000);

        // verify dữ liệu immigration
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
                .getAttribute("value"),number);
        Assert.assertTrue(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']"))
                .getAttribute("value").contains(comments)); // vẫn có thể dùng assertEquals

        // logout
        driver.findElement(By.cssSelector("span[class='oxd-userdropdown-tab']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(3000);

        // login
        driver.findElement(userName).sendKeys(user);
        driver.findElement(password).sendKeys(pwUser);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(3000);

        // verify dữ liệu
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value"),employeeId);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(.,'Number')]/parent::div/following-sibling::div/input"))
                .getAttribute("value"),number);
        Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']"))
                .getAttribute("value"),comments);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
