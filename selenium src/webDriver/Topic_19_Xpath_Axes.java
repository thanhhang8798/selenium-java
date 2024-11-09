package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_Xpath_Axes {
    // Axes dùng dấu "::", thường dùng 1 / >> cấu trúc /axes::thẻ tiếp theo
    // dùng ::* sẽ đúng với tất cả các thẻ theo sau đó
    // Nếu đổi vị trí các sản phẩm thì Axes vẫn tìm được => Hạn chế việc UI/ requirement thay đổi ít phải maintain testscript nhất
    // không nên dùng preceding (bác), following (chú)
    // dùng 1 / thay cho child (con)
    // dùng 2 // thay cho descendant (cháu)
    // dùng Axes khi không thể gọi trực tiếp locator đó (vì giá trị đó không cố đinh-bị thay đổi), cần phải dùng qua locator liên quan

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Axes() {
        driver.get("https://demo.guru99.com/access.php?uid=mngr597285%20&%20pass=nAzebyq%20&%20email=thanhhang898@gmail.com");

    // following-sibling: em
        // vì user id và password là giá trị thay đổi nên dùng anh để tìm em

        driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));

    // preceding-sibling: anh
        driver.findElement(By.xpath("//td[text()='User ID :']/parent::tr/preceding-sibling::tr//h2[text()='Access details to demo site.']"));

    // parent: cha
        // vì text "This access is valid only for 20 days." có thể thay đổi số ngày nên dùng parent và following-sibling
        driver.findElement(By.xpath("//td[text()='User ID :']/parent::tr/following-sibling::tr//h3[contains(text(),'This access is valid only for')]"));

    // ancestor: tổ tiên
        driver.findElement(By.xpath("//td[text()='User ID :']/ancestor::table"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
