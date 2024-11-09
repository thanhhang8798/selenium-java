package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_BaiTap_Browser_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Verify_Url() {
        driver.get("https://live.techpanda.org/");

        // click vào My account tại footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // verify url của trang vừa đươc click
            // cách 1: khai báo biến
        String loginPageUrl = driver.getCurrentUrl(); // khai báo biến
        Assert.assertEquals(loginPageUrl, "https://live.techpanda.org/index.php/customer/account/login/"); //verify url

            // cách 2: verify luôn không cần khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
                // hàm assert import testng

        // click button Create an account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        // verify url trang Register page
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Verify title của login page
        Assert.assertEquals(driver.getTitle(),"Customer Login");
            // cách lấy title trong dev tool: tab console > chọn no user messages: viết document.title

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_Navigation() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

        // back lại trang login page
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        // forward tới trang register page (trang vừa rồi)
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();

        // verify login page chừa text "Login or Create an Account"
        // cần phải get source code rồi kiểm tra xem trong đó có chứa text cần tìm hay không
            // verify tuyệt đối: dùng assertEquals
            // verify tương đối: dùng assertTrue/ False sau đó .contains
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
