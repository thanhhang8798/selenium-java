package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Topic_38_User_Interactions_P3 {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
        action = new Actions(driver);
    }

    @Test
    public void TC_01_Click() {
        // có 3 cách để click
        // 1 - dùng web element
        // driver.findElement(By.cssSelector()).click();

        // 2 - dùng action
        // action.click(driver.findElement(By.cssSelector())).perform();

        // 3 - dùng javascript
        // jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("")));
    }

    @Test
    public void TC_02_Scroll() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClick = driver.findElement(By.cssSelector("button[ondblclick='doubleClickMe()']"));

        // nếu chrome/edge thì dùng scroll của action, nếu firefox dùng javascript
        if(driver.toString().contains("Chrome") || driver.toString().contains("Edge")) {
            action.scrollToElement(doubleClick).perform();
            Thread.sleep(3000);
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClick);
        }

        action.doubleClick(doubleClick).perform();
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
    }

    // làm TC 08, 09 https://docs.google.com/document/d/15MqNX4HLiR29Vn2XhFhugTb2AJpAT16tiEGHzQ0GeFo/edit?tab=t.0#heading=h.az90xdyo8cy

    @Test
    public void TC_03_Drag_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.id("draggable"));
        WebElement bigCircle = driver.findElement(By.id("droptarget"));

        // verify text big circle trước khi drag and drop
        Assert.assertEquals(bigCircle.getText(),"Drag the small circle here.");

        // drag and drop
        action.dragAndDrop(smallCircle, bigCircle).perform();

        // verify big circle sau khi drag and drop
            // text
        Assert.assertEquals(bigCircle.getText(),"You did great!");
            // màu background
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color"))
                .asHex().toUpperCase(), "#03A9F4");
    }

    @Test
    public void TC_04_Drag_Drop_HTML5() {
        // không chạy được trên firefox, chỉ chạy được trên chromium
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        action.dragAndDrop(columnA, columnB).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
