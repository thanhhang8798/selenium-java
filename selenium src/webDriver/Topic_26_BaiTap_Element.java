package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_BaiTap_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Kiểm tra phần tử hiển thị
            // email
        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email textbox is displayed");
        } else {
            System.out.println("Email textbox is not displayed");
        }

            // under 18 age
        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 18 is displayed");
        } else {
            System.out.println("Age under 18 is not displayed");
        }

            // Education
        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        } else {
            System.out.println("Education is not displayed");
        }

        // Kiểm tra phần tử không hiển thị
            // Name: user5
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            driver.findElement(By.xpath("//h5[text()='Name: User5']/following-sibling::a")).click();
            System.out.println("User 5 is displayed");
        } else {
            System.out.println("User 5 is not displayed");
        }
    }

    @Test
    public void TC_02_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // kiểm tra phần tử enable
            // email
        if (driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()) {
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }

            // Age under 18
        if (driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled()) {
            System.out.println("Age under 18 is enabled");
        } else {
            System.out.println("Age under 18 is disabled");
        }

            // Education
        if (driver.findElement(By.xpath("//textarea[@id='edu']")).isEnabled()) {
            System.out.println("Education is enabled");
        } else {
            System.out.println("Education is disabled");
        }

            // Job 1
        if (driver.findElement(By.id("job1")).isEnabled()) {
            System.out.println("Job 1 is enabled");
        } else {
            System.out.println("Job 1 is disabled");
        }

            // Interests development checkbox
        if (driver.findElement(By.id("development")).isEnabled()) {
            System.out.println("Interests development checkbox is enabled");
        } else {
            System.out.println("Interests development checkbox is disabled");
        }

            // Slider 01
        if (driver.findElement(By.id("slider-1")).isEnabled()) {
            System.out.println("Slider 01 is enabled");
        } else {
            System.out.println("Slider 01 is disabled");
        }

        // Kiểm tra phần tử disabled
            // Password
        if (driver.findElement(By.id("disable_password")).isEnabled()) {
            System.out.println("Passwordx is enabled");
        } else {
            System.out.println("Password is disabled");
        }

            // Age - radio button is disabled
        if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
            System.out.println("Age - radio button is enabled");
        } else {
            System.out.println("Age - radio butto is disabled");
        }

            // Biography
        if (driver.findElement(By.id("bio")).isEnabled()) {
            System.out.println("Biography is enabled");
        } else {
            System.out.println("Biography is disabled");
        }

            // Job Role 03
        if (driver.findElement(By.id("job3")).isEnabled()) {
            System.out.println("Job Role 03 is enabled");
        } else {
            System.out.println("Job Role 03 is disabled");
        }

            // Interests (checkbox is disabled)
        if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
            System.out.println("Interests (checkbox is disabled) is enabled");
        } else {
            System.out.println("Interests (checkbox is disabled) is disabled");
        }

            // Slider 02
        if (driver.findElement(By.id("slider-2")).isEnabled()) {
            System.out.println("Slider 02 is enabled");
        } else {
            System.out.println("Slider 02 is disabled");
        }
    }

    @Test
    public void TC_03_Selected() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // click chọn
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        // dừng 3s để xem testcase
        Thread.sleep(3000);

        // kiểm tra các phần tử xem được chọn chưa
        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("Radio age under 18 is selected");
        } else {
            System.out.println("Radio age under 18 is de-selected");
        }

        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Checkbox language java is selected");
        } else {
            System.out.println("Checkbox language java is de-selected");
        }

        // click để bỏ chọn Checkbox language java
        driver.findElement(By.cssSelector("input#java")).click();

        // kiểm tra Checkbox language java đã được bỏ chọn chưa
        if (driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Checkbox language java is selected");
        } else {
            System.out.println("Checkbox language java is de-selected");
        }
    }

    @Test
    public void TC_04_Page_Source() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("Zxcvbnm123@gmail.com");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("button#create-account-enabled")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("button#create-account-enabled"))); // lệnh scroll

        // test trường password
        // ký tự số
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

        // ký tự thường
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // ký tự in hoa
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // ít nhất 8 ký tự
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC123456");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // gồm username
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Zxcvbnm123@gmail.com");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

        // gồm ký tự đặc biệt
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // đáp ứng đủ điều kiện
        Thread.sleep(3000); // ngắt quãng để xem chạy testcase
        driver.findElement(By.cssSelector("input#new_password")).clear();  // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Studyautomation@000");
        driver.findElement(By.cssSelector("label[title='Show Password']")).click();

            // lưu ý dùng hàm assertFalse
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
