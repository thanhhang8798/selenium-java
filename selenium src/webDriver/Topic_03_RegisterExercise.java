package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_RegisterExercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Empty_Field() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        // actual - expected result
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Thanh Hằng");
        driver.findElement(By.name("txtEmail")).sendKeys("hang@vn.vn.net..");
        driver.findElement(By.name("txtCEmail")).sendKeys("hang@vn.vn.net..");
        driver.findElement(By.name("txtPassword")).sendKeys("112233");
        driver.findElement(By.name("txtCPassword")).sendKeys("112233");
        driver.findElement(By.name("txtPhone")).sendKeys("0989999999");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại email hợp lệ");
    }

    @Test
    public void TC_03_Incorrect_Confirm_Email () {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Thanh Hằng");
        driver.findElement(By.name("txtEmail")).sendKeys("hang@gmail.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("hang@gmail.vn");
        driver.findElement(By.name("txtPassword")).sendKeys("112233");
        driver.findElement(By.name("txtCPassword")).sendKeys("112233");
        driver.findElement(By.name("txtPhone")).sendKeys("0989999999");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Invalid_Passwordl() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Thanh Hằng");
        driver.findElement(By.name("txtEmail")).sendKeys("hang@gmail.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("hang@gmail.vn");
        driver.findElement(By.name("txtPassword")).sendKeys("11223");
        driver.findElement(By.name("txtCPassword")).sendKeys("11223");
        driver.findElement(By.name("txtPhone")).sendKeys("0989999999");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_ConfirmPasswordl() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Thanh Hằng");
        driver.findElement(By.name("txtEmail")).sendKeys("hang@gmail.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("hang@gmail.vn");
        driver.findElement(By.name("txtPassword")).sendKeys("112233");
        driver.findElement(By.name("txtCPassword")).sendKeys("112234");
        driver.findElement(By.name("txtPhone")).sendKeys("0989999999");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_PhoneNumber() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Case 1 - less than 10 chars
        driver.findElement(By.name("txtFirstname")).sendKeys("Thanh Hằng");
        driver.findElement(By.name("txtEmail")).sendKeys("hang@gmail.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("hang@gmail.vn");
        driver.findElement(By.name("txtPassword")).sendKeys("112233");
        driver.findElement(By.name("txtCPassword")).sendKeys("112233");
        driver.findElement(By.name("txtPhone")).sendKeys("098999999");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Case 2 - more than 10 chars
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("098999999000");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Case 3 - contains text
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("09345E6789");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập con số");

        // Case 4 - not start with 0xxx
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("0034556789");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
