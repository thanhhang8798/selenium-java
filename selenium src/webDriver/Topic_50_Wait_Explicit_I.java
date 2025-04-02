package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_50_Wait_Explicit_I {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {
        // chờ cho 1 alert xuất hiện trong html + sau đó switch vào
        explicitWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // chờ cho 1 element ở dạng clickable (button/ checkbox/ radio/ link/ image...) + sau đó click vào
        // elementToBeClickable dùng kiểu By
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(""))).click();

        // chờ cho 1 element ở dạng visible - hiển thị (all element) => get text/ css/ attribute/ displayed
        // element dùng kiểu By
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))).getText();
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
            // nhiều element => dùng visibilityOfAllElements
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(""))));

        // chờ cho 1 element ở dạng invisible - không hiển thị (all element)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());
            // nhiều element => dùng invisibilityOfAllElements
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));

        // chờ cho 1 element ở dạng selected (checkbox/ radio) => trước khi verify checkbox/ radio đã được chọn hay chưa
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

        // chờ cho 1 element ở dạng presence (all element in html)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // element size: wait cho đủ số lượng element trước rồi mới verify size
        // ước chừng > hoặc < thì dùng hàm more than/ less than
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),15));
        Assert.assertEquals(driver.findElements(By.cssSelector("")).size(),15);

        // attribute: chờ cho attribute đúng giá trị nào đó, sau đó verify
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"value", "Hằng"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("value"),"Hằng");

        // text: chờ cho text đúng giá trị nào đó, sau đó verify
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"xinh đẹp tuyệt vời"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"xinh đẹp tuyệt vời");
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
