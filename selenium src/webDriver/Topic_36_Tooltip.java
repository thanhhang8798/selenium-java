package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_36_Tooltip {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:\\Users\\THANH HANG\\AppData\\Local\\Microsoft\\Edge\\User Data\\");
        edgeOptions.addArguments("--profile-directory=Profile 2");
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        action = new Actions(driver);
    }

    // làm TC 01, 2, 3 topic 10 https://docs.google.com/document/d/15MqNX4HLiR29Vn2XhFhugTb2AJpAT16tiEGHzQ0GeFo/edit?tab=t.0#heading=h.bkl0f42t149z

    @Test
    public void TC_01_Tooltip_Text() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        // hover textbox để hiện tooltip, pause lại để thấy tooltip
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).pause(Duration.ofSeconds(3)).perform();

        // verify text của tooltip
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Tooltip_Click() throws InterruptedException {
//        driver.get("https://www.myntra.com/"); // trang chặn chạy automation
//        action.moveToElement(driver.findElement(By.cssSelector("a[data-group='kids']"))).perform();
//
//        // click vào 1 element trong tooltip
//        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
//        Thread.sleep(3000);
//
//        // verify đến trang tiếp theo
//        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover() {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']"))).perform();
        action.click(driver.findElement(By
                .xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Gọt Bút Chì']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"GỌT BÚT CHÌ");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
