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

public class Topic_50_Wait_Explicit_III {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // wait cho calender hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.rcMainTable")));

        // wait cho text hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.label"),"No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),"No Selected Dates to display.");

        // wait để click vào ngày/ tháng/ năm
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='31']"))).click();

        // wait để loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        // wait cho text cập nhật lại ngày
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.label"),"Monday, March 31, 2025"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),"Monday, March 31, 2025");

        // verify ngày được chọn
        explicitWait.until(ExpectedConditions.attributeToBe(By.xpath("//a[text()='31']/parent::td")
                ,"class","rcSelected rcHover"));
        // có rchover là vì khi chạy auto click xong vẫn để chuột ở đó nên hiện rcHover
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='31']/parent::td"))
                .getDomAttribute("class"),"rcSelected rcHover");
    }

    @Test
    public void TC_02_Elements() {
        driver.get("https://gofile.io/home");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // khi vừa mở trang hiển thị icon loading
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // load file lên
        String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\"; // thư mục upload file
        String screenshot = "chup man hinh.png";
        String koreaImg = "ảnh hàn quốc.jpg";
        String screanshotPath = uploadFilePath + screenshot;
        String koreaImgPath = uploadFilePath + koreaImg;

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(screanshotPath + "\n" + koreaImgPath);

        // wait cho tất cả progress bar biến mất, sau đó vẫn hiện icon loading
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // wait cho text upload complete hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center>h2"),"Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text-center>h2")).getText(),"Upload Complete");

        // click vào folder link
        driver.findElement(By.cssSelector("div.items-center.text-sm>a")).click();

        // wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // wait cho hiển thị ảnh
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("div.truncate>a"))));
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + koreaImg + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + screenshot + "']")).isDisplayed());
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
