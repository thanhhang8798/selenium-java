package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_37_User_Interactions {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
        action = new Actions(driver);
    }
    // làm TC 04, 5, 6, 7 topic 10 https://docs.google.com/document/d/15MqNX4HLiR29Vn2XhFhugTb2AJpAT16tiEGHzQ0GeFo/edit?tab=t.0#heading=h.bkl0f42t149z

    @Test
    public void TC_01_ClickAndHold_Fix() {
        // kéo và di chuột để chọn các số
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        // click and hole
        action.clickAndHold(numbers.get(4)) // click và giữ chuột tại element 5 (index là 4)
                .moveToElement(numbers.get(11)) // di chuột đến elemeent thứ 12
                .release() // release để nhả chuột ra
                .perform(); // thực thi các câu lệnh trên

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        // verify số lượng number được chọn
        Assert.assertEquals(numberSelected.size(),8);
    }

    @Test
    public void TC_02_ClickAndHold_Random() {
        // nhấn phím ctrl để chọn số, verify số lượng đã chọn
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        String osName = System.getProperty("os.name"); // khai báo biến hệ điều hành
        Keys key = null;

        if (osName.contains("Window")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND; // hệ điều hành OS dùng lệnh command
        }

        // nhấn phím control xuống
        action.keyDown(key).perform();

        // nhấn liên tiếp các số muốn chọn
        action.click(numbers.get(2)).click(numbers.get(5)).click(numbers.get(11)).click(numbers.get(19)).perform();

        // thả phím control
        action.keyUp(key).perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numberSelected.size(),4);
    }

    @Test
    public void TC_03_ClickAndHold_Random_Verify() {
        // nhấn phím ctrl để chọn số, verify chính xác những số được chọn
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        String osName = System.getProperty("os.name"); // khai báo biến hệ điều hành
        Keys key = null;

        if (osName.contains("Window")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        // nhấn phím control xuống
        action.keyDown(key).perform();

        // actual number: nhấn liên tiếp các số muốn chọn: 3, 6, 12, 20
        List<String> actualNumber = new ArrayList<String>();
        actualNumber.add("3");
        actualNumber.add("6");
        actualNumber.add("12");
        actualNumber.add("20");

        for (String number : actualNumber) {
            action.click(numbers.get(Integer.parseInt(number) - 1)); // chuyển từ kiểu string sang int
        }

        // thả phím control
        action.keyUp(key).perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

        // expected numbers: 3, 6, 12, 20
        List<String> expertedNumber = new ArrayList<String>();

        for (WebElement number : numberSelected) {
            expertedNumber.add(number.getText());
        }
        Assert.assertEquals(actualNumber, expertedNumber);
    }

    @Test
    public void TC_04_DoubleClick() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // scroll
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//legend[text()='Broken Images']")));
        Thread.sleep(3000);

        action.doubleClick(driver.findElement(By.cssSelector("button[ondblclick='doubleClickMe()']"))).perform();
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void TC_05_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        By quit = By.cssSelector("li.context-menu-icon-quit");

        // khi chưa click chuột phải, quit không hiển thị
        Assert.assertFalse(driver.findElement(quit).isDisplayed());

        // hàm contextClick: click bằng chuột phải
        action.contextClick(driver.findElement(By.cssSelector("span.btn-neutral"))).perform();

        Assert.assertTrue(driver.findElement(quit).isDisplayed());

        // hover quit button
        action.moveToElement(driver.findElement(quit)).perform();

        // verify quit được hover, element hiển thị context-menu-hover
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());

        // click quit
        action.click(driver.findElement(quit)).perform();
        Thread.sleep(3000);

        // accept alert
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        // verify quit không còn hiển thị nữa
        Assert.assertFalse(driver.findElement(quit).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
