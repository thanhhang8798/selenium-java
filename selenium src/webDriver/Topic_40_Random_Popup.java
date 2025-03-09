package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_40_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_In_DOM() {
        driver.get("https://dehieu.vn/");
        By popup = By.cssSelector("div.modal-header");

        if (driver.findElement(popup).isDisplayed()) {
            // TH1: nếu random popup hiển thị thì close nos đi rồi click vào Đăng nhập
            System.out.println("random popup hiển thị");
            driver.findElement(By.cssSelector("button.close")).click();
        }
            // TH2: nếu random popup KHÔNG hiển thị thì click vào Đăng nhập
        System.out.println("random popup không hiển thị");
        driver.findElement(By.cssSelector("a.signin-site-menu")).click();

        // verify hiển thị form loggin
        Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());
    }

    @Test
    public void TC_02_In_DOM_2() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        By randomPopup = By.cssSelector("div.pop-container"); // dùng element khi load lại trang có hiển thị nhưng ở dạng disable
        Thread.sleep(6000);

        if (driver.findElement(randomPopup).isDisplayed()) {
            // TH1: nếu random popup hiển thị thì close nos đi rồi click vào PC, chọn KMPlayer
            System.out.println("random popup hiển thị");
            driver.findElement(By.cssSelector("div.close")).click();
        }
            // TH2: nếu random popup không hiển thị thì close nos đi rồi click vào PC, chọn KMPlayer
        System.out.println("random popup không hiển thị");
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[@class='pc']/a[text()='KMPlayer']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.sub>h1")).getText(),"KMPlayer - Video Player for PC");
    }

    @Test
    public void TC_03_Not_In_DOM() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(15000);
        By randomPopup = By.cssSelector("div.lepopup-form-inner>div.lepopup-element-2.lepopup-fadeIn");

        if (driver.findElements(randomPopup).size()>0 && driver.findElements(randomPopup).get(0).isDisplayed()) {
            // TH1: nếu random popup hiển thị thì close nó đi
            System.out.println("random popup hiển thị");
            driver.findElement(By.cssSelector("div.lepopup-element-3.lepopup-fadeIn>div.lepopup-element-html-content")).click();
        }
            // TH2: nếu random popup không hiển thị thì search selenium ở textbox search và kiểm tra kết quả
        System.out.println("random popup không hiển thị");
        driver.findElement(By.id("search-input")).sendKeys("Selenium");
        Thread.sleep(2000);
        driver.findElement(By.id("search-submit")).click();
        Thread.sleep(5000);

        // verify kết quả tìm kiếm theo title
        List<WebElement> search = driver.findElements(By.cssSelector("ul#posts-container h2.post-title>a"));
        for (WebElement title : search) {
            System.out.println(title.getText());
        }
    }

    @Test
    public void TC_04_Not_In_DOM2() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        By randomPopup = By.id("support-home"); // dùng element khi load lại trang không hiển thị
        Thread.sleep(6000);

        if (driver.findElements(randomPopup).size()>0 && driver.findElements(randomPopup).get(0).isDisplayed()) {
            // TH1: nếu random popup hiển thị thì close nos đi rồi click vào PC, chọn KMPlayer
            System.out.println("random popup hiển thị");
            driver.findElement(By.cssSelector("div.close")).click();
        }
        // TH2: nếu random popup không hiển thị thì close nos đi rồi click vào PC, chọn KMPlayer
        System.out.println("random popup không hiển thị");
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[@class='pc']/a[text()='KMPlayer']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.sub>h1")).getText(),"KMPlayer - Video Player for PC");
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
