package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_27_BaiTap_Textbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Textbox() {
        // làm TC 02 của topic 07: https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit?tab=t.0#heading=h.fusc17j2zjta
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        // khai báo biến
        String firstName = "Thanh";
        String lastName = "Hang";
        String fullName = firstName + " " + lastName;
        String email = "thanhhang" + new Random().nextInt(9999) + "@gmail.com"; // tạo random số giới hạn đến 9999
        String password = "123456";

        // nhập thông tin vào các trường
        driver.findElement(By.id("firstname")).sendKeys(firstName); // khi send key dùng biến thì k cần dấu ""
        driver.findElement(By.id("lastname")).sendKeys(lastName); // khi truyền giá trị thì phải dùng ""
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        // verify msg thông báo đăng ký thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
                .getText(),"Thank you for registering with Main Website Store.");

        // verify user được tạo
        String userInfor = driver.findElement(By.cssSelector("div.col-1 div.box-content>p")).getText();
        Assert.assertTrue(userInfor.contains(fullName));
        Assert.assertTrue(userInfor.contains(email));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        // nhập giá trị vào các trường đánh giá
        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.id("review_field")).sendKeys("everything is good\n" +
                "I will continue choosing this web to buy\n" +
                "thank you");
            // xuống dòng dùng "\n" + xuống dòng ""
            // hoặc dùng "123\n456\n789"
        driver.findElement(By.id("summary_field")).sendKeys("good");

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        // verify msg summit review
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
                .getText(),"Your review has been accepted for moderation.");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
