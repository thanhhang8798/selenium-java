package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Css {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Css() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // normal
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("[id='email']")); //rút gọn tagname
        driver.findElement(By.xpath("//input[@id='email']"));

        // node con: đi từ cha đến con (1 node) => dùng >
        driver.findElement(By.cssSelector("ul>li>div>input[id='email']"));
        driver.findElement(By.xpath("//ul/li/div/input[@id='email']"));

        // node con: đi từ cha đến con (nhiều node) => dùng dấu cách
        driver.findElement(By.cssSelector("ul input[id='email']"));
        driver.findElement(By.xpath("//ul//input[@id='email']"));

        // kết hợp với ID, sử dụng ký tự đặc biệt => dùng #
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));
        driver.findElement(By.xpath("//input[@id='email']")); //xpath

        // kết hợp với class => dùng dấu chấm, đối với string có dấu cách thì thay dấu cách bằng dấu .
        driver.findElement(By.cssSelector("input[class='input-text required-entry validate-email']"));
        driver.findElement(By.cssSelector("input.input-text.required-entry.validate-email")); //đầy đủ đoạn text
        driver.findElement(By.cssSelector("input.validate-email")); //chỉ lấy text sau vẫn được, miễn sao duy nhất là được
        driver.findElement(By.cssSelector("input.input-text")); //chỉ lấy text đầu, miễn sao duy nhất là được
        driver.findElement(By.cssSelector("input.input-text.validate-email.required-entry")); //đổi thứ tự text vẫn được
        driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")); //xpath

        // nhiều attribute
            // and: thêm dấu ngoặc vuông
        driver.findElement(By.cssSelector("input[id='email'][title='Email Address']"));
        driver.findElement(By.xpath("//input[@id='email' and @title='Email Address']"));

            // or: thêm dấu phẩy
        driver.findElement(By.cssSelector("input[id='email'],input[id='pass']"));
        driver.findElement(By.cssSelector("input[id='email'],[id='pass']"));
        driver.findElement(By.xpath("//input[id='email' or @id='pass']"));

            // not: dùng :not()
        driver.findElement(By.cssSelector("input:not([id='email'])"));
        driver.findElement(By.xpath("//input[not(@id='email')]"));


        // contains: css chỉ dùng với attribute, không dùng được với text => dùng *=
        driver.findElement(By.cssSelector("input[class*='input-text']"));
        driver.findElement(By.xpath("//input[contains(@class,'input-text')]"));

            // starts-with: dùng ^=
        driver.findElement(By.cssSelector("input[class^='input-text']"));
        driver.findElement(By.xpath("//input[starts-with(@class,'input-text')]"));

            // ends-with: xpath không support, nhưng Css dùng $=
        driver.findElement(By.cssSelector("input[class$='validate-email']"));
    }

    @Test
    public void TC_02_Css2() {
        driver.get("http://live.techpanda.org/index.php/mobile.html");

        // following-sibling: dùng + hoặc ~
            // dùng ~ sẽ lấy tất cả em, giống xpath
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2~div")); //ra 3 kết quả
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div")); //ra 3 kết quả
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div/h2/following-sibling::div")); //ra 3 kết quả

            // dùng + chỉ lấy node em gần kề nhất
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2+div")); //ra 1 kết quả
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2+div")); //ra 1 kết quả

        // Css không hỗ trợ đi ngược node lên: preceding-sibling, parent, ancestor


        // index: css không support index khác node cha, chỉ áp dụng cho cùng node cha
            // nếu node con cùng thẻ thì cách dùng -child và -of-type không khác gì nhau

            // index chỉ đếm cùng tag => dùng :nth-of-type()
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:nth-of-type(2)")); // ra div  class price-box
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div/h2/following-sibling::div[2]"));

            // index đếm tất cả các node con nhưng nếu khác tag sẽ không tìm thấy => dùng :nth-child()
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:nth-child(2)")); // ra div  class rating
        // driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:nth-child(1)")); //dùng index 1 sẽ không thấy vì tag là h2 (không phải div)

            // đầu tiên: dùng first-of-type (chỉ tính cùng tagname) hoặc first-child
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:first-of-type"));
        //driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:first-child")); // không ra vì không đúng tagname

            // cuối cùng: dùng last-of-type (tính tất cả node con nhưng nếu cái cuối khác tagname thì không tìm ra) hoặc last-child
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:last-of-type"));
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:last-child"));
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div/h2/following-sibling::div[last()]"));

            // chẵn (even), lẻ (odd): dùng nth-of-type(odd) hoặc nth-child(even), xpath không dùng được
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:nth-of-type(odd)"));
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>h2~div:nth-child(even)"));
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
