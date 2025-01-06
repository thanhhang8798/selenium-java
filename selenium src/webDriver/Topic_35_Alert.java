package webDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_35_Alert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    // làm TC 7, 8, 9, 11 topic 09 https://docs.google.com/document/d/1kPgRirztWIC9R_XiZFNYI3E0KVWfrzf2x_Het5MRj3s/edit?tab=t.0
    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // hàm wait: chờ cho 1 alert được xuất hiện trong HTML. hàm này bao gồm cả switch vào alert
        Alert acceptAlert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        // switch sang alert: driver.switchTo().alert();

        // verify text của alert
        Assert.assertEquals(acceptAlert.getText(),"I am a JS Alert");

        // accept alert
        acceptAlert.accept();

        // verify text sau khi accept alert
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert confirmAlert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(confirmAlert.getText(),"I am a JS Confirm");

        // đóng alert
        confirmAlert.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert promptAlert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(promptAlert.getText(),"I am a JS prompt");

        // nhập vào textbox
        String inputTextbox = "ABC";
        promptAlert.sendKeys(inputTextbox);
        promptAlert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: " + inputTextbox);

        // không nhâp textbox
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert promptAlertNull = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        promptAlertNull.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
    }

    @Test
    public void TC_04_Authentication_Alert() throws InterruptedException {
        String username = "admin";
        String password = "admin";
        driver.get("https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content p"))
                .getText(),"Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_05_Authentication_Alert_Link() {
        String username = "admin";
        String password = "admin";
        driver.get("https://the-internet.herokuapp.com/");
        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        String[] text = authenLink.split("//");
        authenLink = text[0] + "//" + username+":"+password+"@"+text[1];
        driver.get(authenLink);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content p"))
                .getText(),"Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_06_Dismiss_Authentication_Alert() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Alert authenAlert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Thread.sleep(5000);
        authenAlert.dismiss();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
