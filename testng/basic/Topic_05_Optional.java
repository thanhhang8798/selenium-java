package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Topic_05_Optional {
    WebDriver driver;
    Select select;
    String environmentUrl;
    Platform platform;

    // thêm annotation: khi có nhiều tham số thì để dạng mảng {}
    @Parameters({"browser", "environmentName", "platformName"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName, @Optional("Windows") String platformName) {
        System.out.println("Browser name = " + browserName);
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw  new RuntimeException("Browser name is not support");
        }
        switch (environmentName.toUpperCase()) {
            case "DEV":
                environmentUrl = "https://dev.rode.com";
                break;
            case "TEST":
                environmentUrl = "https://test.rode.com";
                break;
            case "PROD":
                environmentUrl = "https://rode.com";
                break;
            default:
                throw  new RuntimeException("Environment name is not support");
        }

        switch (platformName.toUpperCase()) {
            case "MAC":
                platform = Platform.MAC;
                break;
            case "ANDROID":
                platform = Platform.ANDROID;
                break;
            case "WINDOWS":
                platform = Platform.WINDOWS;
                break;
            case "IOS":
                platform = Platform.IOS;
                break;
            case "LINUX":
                platform = Platform.LINUX;
                break;
            default:
                throw  new RuntimeException("Platform name is not support");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    @Test
    public void TC_01_() {
        driver.get(environmentUrl + "/en/support/where-to-buy");

        select = new Select(driver.findElement(By.id("country")));
        Assert.assertFalse(select.isMultiple());
        select.selectByContainsVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

        driver.findElement(By.id("map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.cssSelector("button[class='btn btn-default']")).click();

        // verify kết quả tìm kiếm
        List<WebElement> dealerBranches = driver.findElements(By.cssSelector("div.dealer_branch h4"));
        Assert.assertEquals(dealerBranches.size(),16);

        // vòng lặp For-each
        for(WebElement dealerName: dealerBranches) {
            System.out.println(dealerName.getText());
        }
    }


    @AfterClass
    public void cleanbrowser() {
        driver.quit();
    }
}
