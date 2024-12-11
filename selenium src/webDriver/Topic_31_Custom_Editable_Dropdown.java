package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_31_Custom_Editable_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_EditableDropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        // parentchild phải để locator nhập vào textbox
        selectItemInEditableDropdown("input.search", "div.item>span.text", "Aland Islands");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aland Islands");

        selectItemInEditableDropdown("input.search", "div.item>span.text", "Belize");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belize");
    }

    private void selectItemInEditableDropdown(String parentLocator, String childLocator, String textItem) {
        // đổi với editable dropdown (có thể search và input text), nên dùng sendkeys thay vì click
        // trước khi sendkeys phải clear
        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(textItem);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allIteams = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : allIteams) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                //Thread.sleep(1500);
                break;
            }
        }
    }



    @Test
    public void TC_02_Huawai() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        selectItemInNationDropdown("div[ht='input_emailregister_dropdown']",
                "input[ht='input_emailregister_search']", "div.hwid-dropInpunt span.list-item-text", "Quần đảo Cayman");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText(),"Quần đảo Cayman");

        selectItemInNationDropdown("div[ht='input_emailregister_dropdown']",
                "input[ht='input_emailregister_search']", "div.hwid-dropInpunt span.list-item-text", "Singapore");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText(),"Singapore");
    }

    private void selectItemInNationDropdown(String parentLocator, String searchInput, String childLocator, String textItem) throws InterruptedException {
        // vì trang load lâu nên chờ cho load được dropdown
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentLocator)));
        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(searchInput)).clear();
        driver.findElement(By.cssSelector(searchInput)).sendKeys(textItem);
        Thread.sleep(2000);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> nationItems = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : nationItems) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
