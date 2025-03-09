package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_41_Shadow {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_shadow() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        // tìm element không chứa trong shadow
        String scrollText = driver.findElement(By.cssSelector("a[href='scroll.html']")).getText();
        System.out.println(scrollText);

        // tìm element chứa shadow đầu tiên
        WebElement firstShadowHostElement = driver.findElement(By.id("shadow_host"));

        // lấy ra shadow đầu tiên
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        // tìm element nằm trong shadow
        firstShadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("abc");

        // tìm element chứa shadow thứ 2 - không được dùng driver để tìm, dùng shadow 1
        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));

        // lấy ra shadow thứ 2
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        // thao tác với element nằm trong shadow thứ 2
        String someText = secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(someText);
    }

    @Test
    public void TC_02_Many_Shadow() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);
        // shadow 1
        WebElement firstShadowElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext firstShadowRoot = firstShadowElement.getShadowRoot();
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");

        // shadow 2
        WebElement secondShadowElement = firstShadowRoot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));
        SearchContext secondShadowRoot = secondShadowElement.getShadowRoot();
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        // shadow 3, 4: verify kết quả tìm kiếm
        WebElement thirdShadowElement = firstShadowRoot.findElement(By.cssSelector("main.main-content>book-explore"));
        SearchContext thirdShadowRoot = thirdShadowElement.getShadowRoot();

        List<WebElement> forthShadowElements = thirdShadowRoot.findElements(By.cssSelector("book-item"));
        for (WebElement book : forthShadowElements) {
            SearchContext forthShadowRoot = book.getShadowRoot();
            System.out.println(forthShadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
