package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_29_Default_Checkbox {
    WebDriver driver;
    Select select; // dùng thư viện Select để handle default checkbox

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:\\Users\\THANH HANG\\AppData\\Local\\Microsoft\\Edge\\User Data\\");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_theory() throws InterruptedException {
        driver.get("https://egov.danang.gov.vn/reg");

        // gán biến dropdown cho thư viện Select
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));

        // chọn giá trị dropdown: có 3 cách nhưng nên dùng Text
        select.selectByIndex(4);
        Thread.sleep(3000);

        select.selectByValue("11433");
        Thread.sleep(3000);

        select.selectByVisibleText("thành phố Hải Phòng");
        Thread.sleep(3000);

        // getFirstSelectedOption: sau khi chọn xong giá trị dropdown, cần varify lại chọn đúng hay chưa
        // kết hợp với hàm getText và assertEquals để verify
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"thành phố Hải Phòng");

        // isMultiple: kiểm tra dropdown sigle/ multiple
        Assert.assertFalse(select.isMultiple());

        // getOption: lấy ra tất cả item trong dropdown
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen"))); // ghi đè quận huyện lên tỉnh thành
        select.getOptions();
    }

    @Test
    public void TC_02_BaiTap_TC03() {
    // làm TC 03 https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit?tab=t.0#heading=h.fusc17j2zjta
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        String firstName = "Bui";
        String lastName = "Hang";
        String email = "buihang" + new Random().nextInt(9999) + "@gmail.com";
        String password = "abc123";
        String day = "11";
        String month = "May";
        String year = "2024";

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        // chọn dropdown ngày tháng năm
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")))
                .getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")))
                .getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")))
                .getFirstSelectedOption().getText(), year);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
