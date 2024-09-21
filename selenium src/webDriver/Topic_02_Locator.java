package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_ID() { //dùng khi id là duy nhất, không bị trùng lặp
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.id("textfield"));
    }

    @Test
    public void TC_02_Class() { //class phải là duy nhất
        driver.get("https://ngoaingu24h.vn/khoa-hoc/sach-bo-tro-tieng-anh-12-theo-ct-gdpt-2018--4545206226518016");
        driver.findElement(By.className("line-bottom"));
    }

    @Test
    public void TC_03_Name() { //name phải là duy nhất, contribute là name
        driver.get("https://www.facebook.com/reg/");
        driver.findElement(By.name("lastname"));
        driver.findElement(By.name("reg_email__"));
    }

    @Test
    public void TC_04_Link() {
        //chỉ dùng được với link có text, truyền tuyệt đối link text vào
        // phải lấy text trên UI chứ không phải HTML. giống cả viết hoa và thường
        // thường không dùng locator này, vì css và xpath đã bao gồm rồi
        driver.get("https://www.facebook.com/reg/");
        driver.findElement(By.linkText("Already have an account?"));
        driver.findElement(By.linkText("Privacy Policy"));
    }

    @Test
    public void TC_05_Partial_Link() {
        //chỉ dùng được với link có text, truyền tương đối/1 phần link text vào
        // phải lấy text trên UI chứ không phải HTML, iống cả viết hoa và thường
        // thường không dùng locator này, vì css và xpath đã bao gồm rồi
        driver.get("https://www.facebook.com/reg/");
        driver.findElement(By.partialLinkText("account"));
        driver.findElement(By.partialLinkText("Policy"));
    }

    @Test
    public void TC_06_Tagname() { // thường không dùng locator này, vì css và xpath đã bao gồm rồi
        // tìm nhiều element có tên thẻ giống nhau. >> dùng findElement có s

        driver.get("https://www.facebook.com/reg/");
        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số lượng đường link = "+ linkNumber); //đường link dùng thẻ a

        // tagname giống nhau nhưng element khác nhau
        // VD: thẻ input gồm: textbox, checkbox, radio, button, upload file, slider...
        // lúc này dùng css và xpath
    }

    @Test
    public void TC_07_Css() { //tìm nhiều nên dùng findElement có s
        driver.get("https://www.facebook.com/reg/");
        int radioButton = driver.findElements(By.cssSelector("input[type='radio']")).size();
        System.out.println("Tổng số lượng radio = " + radioButton);
    }


    @Test
    public void TC_07a_CssID() { // CSS vs ID
        // ký tự viết tắt của id: dùng # hoặc input#
        // VD: input#name hoặc #name
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("input[id='textfield']"));
        driver.findElement(By.cssSelector("#textfield"));
        driver.findElement(By.cssSelector("input#textfield"));
    }

    @Test
    public void TC_07b_CssClass() { // Css vs Class
        // ký tự viết tắt của class: dùng .
        // VD: .first-name hoặc div.first-name
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("div[class='course-option-item  ']"));
        driver.findElement(By.cssSelector("div.course-option-item  "));
        driver.findElement(By.cssSelector(".course-option-item  "));

    //ngoại lệ: khi class có dấu cách >> thay khoảng trắng bằng .
        driver.findElement(By.cssSelector("div[class='course-option-item active ']"));
        driver.findElement(By.cssSelector("div.course-option-item.active "));
        driver.findElement(By.cssSelector("div.active "));
        driver.findElement(By.cssSelector(".course-option-item.active "));
    }

    @Test
    public void TC_07c_CssName() { // Css vs Name
        // chỉ được viết đúng kiểu của Css, không có viết tắt
        driver.get("https://www.facebook.com/reg/");
        driver.findElement(By.cssSelector("input[name='firstname']"));
        driver.findElement(By.cssSelector("input[name='reg_email__']"));
    }

    @Test
    public void TC_07d_CssLink() { // Css vs Link
    // Css không dùng được với link text >> phải dùng với href
        driver.get("https://www.facebook.com/reg/");
        driver.findElement(By.cssSelector("a[href='/legal/terms/update']"));

    //partial link: dùng href* cho đoạn giữa, dùng href^ cho đoạn link đầu, dùng href$ cho đoạn cuối
        driver.findElement(By.cssSelector("a[href*='/terms/']"));
        driver.findElement(By.cssSelector("a[href^='/legal/terms/']"));
        driver.findElement(By.cssSelector("a[href$='/terms/update']"));
    }

    @Test
    public void TC_07e_CssTagname() { // Css vs Tagname
        driver.get("https://www.facebook.com/reg/");
        int linkNumber = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Tổng số lượng đường link = "+ linkNumber);

    // có nhiều tagname giống nhau nhưng element khác nhau
        int textboxNumber = driver.findElements(By.cssSelector("input[type='text']")).size();
        System.out.println("Tổng số lượng textbox = " + textboxNumber);
    }


    @Test
    public void TC_08_Xpath() { // Xpath không có viết tắt
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    // Xpath vs ID
        driver.findElement(By.xpath("//input[@id='txtEmail']"));

    // Xpath vs Class
        driver.findElement(By.xpath("//input[@class='text form-control']"));

    // Xpath vs Name
        driver.findElement(By.xpath("//input[@name='txtEmail']"));

    // Xpath vs Link
        driver.findElement(By.xpath("//a[@href='https://alada.vn/chinh-sach-bao-mat.html']"));
        driver.findElement(By.xpath("//a[text()='chính sách']")); //dùng text trong html chứ không phải ở UI

    // Xpath vs Partial link
        driver.findElement(By.xpath("//a[starts-with(@href,'https://alada.vn/chinh-sach')]")); // 1 phần đầu
        driver.findElement(By.xpath("//a[contains(@href,'/chinh-sach-')]")); // phần giữa
        // Xpath không support ends-with nhưng Css có (dùng href$)

    // Xpath vs Tag name
        int linkNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
