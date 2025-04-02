package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("");

    }

    @Test
    public void TC_02_() {
        driver.get("");
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
