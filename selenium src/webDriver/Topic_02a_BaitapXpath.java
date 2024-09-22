package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02a_BaitapXpath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // radio button male
        driver.findElement(By.xpath("//input[@id='gender-male']"));

        // radio button female
        driver.findElement(By.xpath("//input[@id='gender-female']"));

        // textbox First name
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        // textbox Last name
        driver.findElement(By.xpath("//input[@id='LastName']"));

        // dropdown Day
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));

        // dropdown Month
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));

        // dropdown Year
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        // textbox email
        driver.findElement(By.xpath("//input[@id='Email']"));

        // textbox Conpany name
        driver.findElement(By.xpath("//input[@id='Company']"));

        // checkbox newsletter
        driver.findElement(By.xpath("//input[@id='Newsletter']"));

        // textbox Password
        driver.findElement(By.xpath("//input[@id='Password']"));

        // textbox Confirm password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));

        // button register
        driver.findElement(By.xpath("//button[@id='register-button']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
