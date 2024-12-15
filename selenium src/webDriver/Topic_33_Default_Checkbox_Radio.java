package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_33_Default_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(5000);
        // scroll xuống element cần thực hiện test, nếu không khi chạy sẽ báo lỗi không tìm thấy element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("ul.tabstrip-items")));

        // chọn locator của checkbox là thẻ input
        By dualzoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        driver.findElement(dualzoneCheckbox).click();
        Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());

        // bỏ chọn checkbox => click lại lần nữa
        driver.findElement(dualzoneCheckbox).click();
        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());

        // cách dùng kết hợp với hàm if để chọn checkbox
        // nếu element chưa được chọn thì mới click
        // nếu chọn rồi thì không click nữa
        if (!driver.findElement(dualzoneCheckbox).isSelected()) { // dùng dấu ! để phủ định hàm phía sau
            driver.findElement(dualzoneCheckbox).click();
        }

        // bỏ chọn checkbox
        if (driver.findElement(dualzoneCheckbox).isSelected()) { // dùng dấu ! để phủ định hàm phía sau
            driver.findElement(dualzoneCheckbox).click();
        }
    }

    @Test
    public void TC_02_Default_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("ul.tabstrip-items")));

        By petrolRadio = By.id("engine3");
        driver.findElement(petrolRadio).click();
        Assert.assertTrue(driver.findElement(petrolRadio).isSelected());

        // cách dùng kết hợp với hàm if để verify
        if (!driver.findElement(petrolRadio).isSelected()) {
            driver.findElement(petrolRadio).click();
        }
    }

    @Test
    public void TC_03_Radio_Summer() {
        driver.get("https://material.angular.io/components/radio/examples");
        By summer = By.cssSelector("input[value='Summer']");
        if(!driver.findElement(summer).isSelected()) {
            driver.findElement(summer).click();
        }
        Assert.assertTrue(driver.findElement(summer).isSelected());
    }

    @Test
    public void TC_04_Click_TwoCheckbox() {
        driver.get("https://material.angular.io/components/checkbox/examples");
        By checked = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminate = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        if (!driver.findElement(checked).isSelected()) {
            driver.findElement(checked).click();
        }
        Assert.assertTrue(driver.findElement(checked).isSelected());

        if (!driver.findElement(indeterminate).isSelected()) {
            driver.findElement(indeterminate).click();
        }
        Assert.assertTrue(driver.findElement(indeterminate).isSelected());

        // bỏ chọn checkbox
        if (driver.findElement(checked).isSelected()) {
            driver.findElement(checked).click();
        }
        Assert.assertFalse(driver.findElement(checked).isSelected());

        if (driver.findElement(indeterminate).isSelected()) {
            driver.findElement(indeterminate).click();
        }
        Assert.assertFalse(driver.findElement(indeterminate).isSelected());
    }

    @Test
    public void TC_05_AllCheckbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // lấy 1 element đại diện cho tất cả list element
        List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));

        // select tất cả checkbox
        for (WebElement checkbox : allCheckboxs) { //checkbox là tên biến đại diện cho cả list checkbox
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // Verify checkbox đã được chọn
        for (WebElement checkbox : allCheckboxs) {
            Assert.assertTrue(checkbox.isSelected());
        }
        // bỏ chọn all checkbox
        for (WebElement checkbox : allCheckboxs) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // verfiy all checkbox đã được bỏ chọn
        for (WebElement checkbox : allCheckboxs) {
            Assert.assertFalse(checkbox.isSelected());
        }

        // chỉ chọn 1 checkbox
        // trong hàm if cần tìm đúng element của checkbox đó và thỏa mãn điều kiện chưa được chọn
        for (WebElement checkbox : allCheckboxs) {
            if (checkbox.getDomAttribute("value").equals("Lung Disease") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // verify checkbox đó đã được chọn
        for (WebElement checkbox : allCheckboxs) {
            if (checkbox.getDomAttribute("value").equals("Lung Disease")) {
                Assert.assertTrue(checkbox.isSelected());
            }
        }

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
