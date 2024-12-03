package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26a_BaiTap_Login {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Empty() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        // verify massages
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email"))
                .getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass"))
                .getText(),"This is a required field.");
    }

    @Test
    public void TC_02_InvalidEmail() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();

        // nhập email, password
        driver.findElement(By.cssSelector("input#email")).sendKeys("12341234@12312.456");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        // verify error msg email
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email"))
                .getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_VerifyPassword() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();

        // nhập email, password
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        // verify error msg email
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass"))
                .getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Incorrect() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account'")).click();

        // nhập email, password
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        // verify error msg email
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span"))
                .getText(),"Invalid login or password."); // lúc tìm element phải tìm đúng element chứa text
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
