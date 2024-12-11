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

public class Topic_30b_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // gọi hàm
        // dropdown select a title
        selectItemInDropdown("salutation-button", "ul#salutation-menu div", "Dr.");
        selectItemInDropdown("salutation-button", "ul#salutation-menu div", "Mr.");
        selectItemInDropdown("salutation-button", "ul#salutation-menu div", "Other");

        // verify giá trị dropdown được chọn
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text"))
                .getText(),"Other");
    }

    private void selectItemInDropdown(String parentLocator, String childLocator, String textItem) {
        driver.findElement(By.id(parentLocator)).click();

        // chờ các item trong dropdown load ra hết
        // lưu ý khi tìm element cần tìm đến locator có chứa text
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        // tìm và lấy ra hết tất cả item bên trong và lưu vào 1 biến (kiểu dữ liệu List)
        // khai báo biến dạng list (tương tự như khai báo kiểu webElement nhưng dành cho nhiều locators)
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) { // kiểm tra điều kiện text lấy ra
                item.click();
                break; // break để thoát khỏi vòng lặp for
            }
        }
    }

    @Test
    public void TC_02_reactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemFriend("div.dropdown", "div.item>span.text", "Jenny Hess");
        selectItemFriend("div.dropdown", "div.item>span.text", "Justen Kitsune");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.dropdown>div.text")).getText(),"Justen Kitsune");
    }

    private void selectItemFriend(String parentLocator, String childLocator, String itemFriend) {
        driver.findElement(By.cssSelector(parentLocator)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        List<WebElement> friend = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : friend) {
            if (item.getText().equals(itemFriend)) {
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_03_vueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        slectCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

        slectCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

    }

    private void slectCustomDropdown(String parentLocator, String childLocator, String option) {
        driver.findElement(By.cssSelector(parentLocator)).click();
        List<WebElement> customDropdown = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : customDropdown) {
            if (item.getText().trim().equals(option)) {  // dùng trim() để xóa khoảng trống của text
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
