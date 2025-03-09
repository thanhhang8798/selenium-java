package tips;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_SplitUrl {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_cloudFlare() throws InterruptedException {
        String authenLink = "https://the-internet.herokuapp.com/";
        String username = "admin";
        String password = "admin";

        // tách url
        String[] text = authenLink.split("//");
        System.out.println(text[0]);
        System.out.println(text[1]);

        // gán user, password vào url
        authenLink = text[0] + "//" + username + ":" + password + "@" + text[1];
        System.out.println(authenLink);
        driver.get(authenLink);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
