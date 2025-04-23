package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_53_Wait_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:\\Users\\THANH HANG\\AppData\\Local\\Microsoft\\Edge\\User Data\\");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_One_Element() {
        driver.get("https://api.orangehrm.com/");
        explicitWait.until(ExpectedConditions.
                invisibilityOfElementLocated(By.cssSelector("div.spinner-container.container3")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1"))
                .getText(),"OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_Many_Element() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='username']")));
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // vì hàm isAllLoadingIconInvisible trả về kiểu boolean nên dùng assertTrue
        Assert.assertTrue(isAllLoadingIconInvisible());

        // Trang PIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());
        // click checkbox all
        ((JavascriptExecutor) driver) .executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.oxd-table-header-cell input")));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.oxd-table-header-cell input")).isSelected());

        // Trang Time
        driver.findElement(By.xpath("//span[text()='Time']")).click();
        Assert.assertTrue(isAllLoadingIconInvisible());
        Assert.assertTrue(driver.findElement(By.cssSelector("div.orangehrm-container")).isDisplayed());
    }

    @Test
    public void TC_03_Javascript() {
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        driver.findElement(By.cssSelector("input.email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input.password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        // chuyển qua trang product
        driver.get("https://admin-demo.nopcommerce.com/Admin/Product/List");
        Assert.assertTrue(isPageLoadedSuccess());
    }

    public boolean isAllLoadingIconInvisible() {
        return explicitWait.until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor =(JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
