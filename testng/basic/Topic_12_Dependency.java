package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Dependency {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_register() {
        System.out.println("register new account");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01_register")
    public void TC_02_order() {
        System.out.println("new order");
    }

    @Test(dependsOnMethods = {"TC_01_register", "TC_02_order"})
    public void TC_03_pay() {
        System.out.println("pay order");
    }

    @Test
    public void TC_04_ship() {
        System.out.println("ship");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
