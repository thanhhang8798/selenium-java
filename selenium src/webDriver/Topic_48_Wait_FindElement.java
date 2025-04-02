package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_48_Wait_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_FindElements() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        // tìm thấy 1 element => trả về 1 element
        List<WebElement> element = driver.findElements(By.cssSelector("input#email"));
        // in ra xem có bao nhiêu element trong list
        System.out.println(element.size());

        // tìm thấy 0 element
        List<WebElement> dontHaveElement = driver.findElements(By.cssSelector("input#emailAddress"));
        System.out.println(dontHaveElement.size());
        // verify k tìm thấy element
        Assert.assertEquals(dontHaveElement.size(),0);

        // nhiều element
        List<WebElement> elementList = driver.findElements(By.cssSelector("input[type='email']"));
        System.out.println(elementList.size());
        // thao tác với list element
        elementList.get(0).sendKeys("automation@gmail.com"); // element đầu tiên
        elementList.get(1).sendKeys("selenium"); // element thứ 2

    }

    @Test
    public void TC_02_() {
        driver.get("");
    }

    @Test
    public void TC_03_() {
        driver.get("");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
