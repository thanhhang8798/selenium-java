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

public class Topic_46_UploadFile {
    WebDriver driver;
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\"; // thư mục upload file
    String screenshot = "chup man hinh.png";
    String koreaImg = "ảnh hàn quốc.jpg";

    String screanshotPath = uploadFilePath + screenshot;
    String koreaImgPath = uploadFilePath + koreaImg;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_eachFile() throws InterruptedException {
    // upload từng file 1
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // load file lên nhưng chưa upload thành công
            // chuột phải vào file cần upload trong thư mục uploadFiles => copy absolute Path
        driver.findElement(By.cssSelector("input[type='file']"))
                .sendKeys("D:\\Automation testing\\02 - Selenium WebDriver\\selenium-java\\uploadFiles\\ảnh hàn quốc.jpg");

            // dùng path để máy nào cũng chạy được
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(screanshotPath);

        // verify sau khi load file lên: tên file đang ở dạng text
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='chup man hinh.png']")).isDisplayed());
            // tách chuỗi để cộng biến vào text()
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + koreaImg + "']")).isDisplayed());

        // upload từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement start : startButtons) {
            start.click();
            Thread.sleep(2000);
        }

        // verify sau khi đã được load lên: tên file chuyển sang dạng link
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ koreaImg + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + screenshot + "']")).isDisplayed());
    }

    @Test
    public void TC_02_multipleFiles() throws InterruptedException {
    // upload 1 lúc nhiều files
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // load file lên nhưng chưa upload thành công
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(screanshotPath + "\n" + koreaImgPath);
        // \n là ký tự xuống dòng

        // verify sau khi load file lên: tên file đang ở dạng text
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='chup man hinh.png']")).isDisplayed());
        // tách chuỗi để cộng biến vào text()
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + koreaImg + "']")).isDisplayed());

        // upload từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement start : startButtons) {
            start.click();
            Thread.sleep(2000);
        }

        // verify sau khi đã được load lên: tên file chuyển sang dạng link
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ koreaImg + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + screenshot + "']")).isDisplayed());
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
