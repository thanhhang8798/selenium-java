package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_30_Default_Checkbox_2 {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        // bỏ qua popup xin quyền vị trí
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("geo.enabled", false);
        option.addPreference("geo.provider.use_corelocation", false);
        driver = new FirefoxDriver(option);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_baiTap() {
    // làm TC 04 https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit?tab=t.0#heading=h.85dnvumg7p9
        driver.get("https://rode.com/en/support/where-to-buy");

        select = new Select(driver.findElement(By.id("country")));

        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

        driver.findElement(By.id("map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        // verify kết quả tìm kiếm
        List<WebElement> dealerBranches = driver.findElements(By.cssSelector("div.dealer_branch h4"));
        Assert.assertEquals(dealerBranches.size(),16);

        // vòng lặp For-each
        for(WebElement dealerName: dealerBranches) {
            System.out.println(dealerName.getText());
        }
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
