package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Xpath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Xpath() {
        driver.get("http://live.techpanda.org/index.php/mobile.html");
    // Xpath tuyệt đối: Sử dụng dấu =
    // dùng xpath tuyệt đối sẽ chạy nhanh hơn
        driver.findElement(By.xpath("//span[text()='Add to Cart']"));

    // Xpath tương đối: chạy chậm hơn vì phạm vi quét rộng hơn

        driver.findElement(By.xpath("//a[contains(text(),'Account')]"));
        driver.findElement(By.xpath("//a[contains(@href,'/account')]"));

        // Starts-with: áp dụng cho value giữ phần đầu
        // khi xuống dòng hoặc có dấu cách thì không tìm được; ở node con không tìm được
        driver.findElement(By.xpath("//a[starts-with(text(),'Register')]"));
        driver.findElement(By.xpath("//a[starts-with(@href,'http://live.techpanda.org/index.php/customer/account/')]"));

        // Ends-with: Xpath không có nhưng Css có sử dụng $=
    }

    @Test
    public void TC_02_Text() {
    // Xpath dùng text trong html, get text dùng ở UI
        driver.get("https://automationfc.github.io/basic-form/");

    // Text tuyệt đối
        driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));

        // concat(): dùng khi text có dấu “” hoặc ‘’. Nháy đơn bọc nháy đôi, Nháy đôi bọc nháy đơn. VD: //span[text()=concat(a,b)]
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));

    // Text tương đối
        // Contain: chứa kí tự đó, áp dụng khi thay đổi url giữa các môi trường;
        // khi xuống dòng hoặc có dấu cách thì vẫn tìm được; ở node con không tìm được
        // index đầu mới lấu được, index sau không lấy được
        driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]"));

        // dùng dấu . : lấy được tất cả
        driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]"));
    }
    @Test
    public void TC_03_FindLocator() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

    // and: //tag[@atribute and @atribute]
        driver.findElement(By.xpath("//a[@class='kd-link' and text()='Online Documentation']"));

    // or: //tag[@atribute or @atribute]
        driver.findElement(By.xpath("//a[@class='kd-link' or text()='Online Documentation']"));

    // not: //tag[not(@atribute)]
        //driver.findElement(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"));
        // dùng debug để tái hiện lúc loading
    }

    @Test
    public void TC_04_Index_InsideParent() {
    // cungf cha
        driver.get("https://automationfc.github.io/jquery-selectable/");
    // index
        driver.findElement(By.xpath("//ol[@id='selectable']/li[15]"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()=15]"));

    // last: dùng để tìm cái cuối cùng
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)]"));

    // gần kề last: là cái trước cái cuối cùng
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()-1]"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)-1]"));
    }


    @Test
    public void TC_05_Index_OutsideParent() {
    // khác cha
        driver.get("http://live.techpanda.org/index.php/mobile.html");

    // index
        // khi không cùng thẻ cha nếu không dùng () thì chỉ tìm được 1 cái đầu tiên
        driver.findElement(By.xpath("//span[text()='Add to Cart'][1]"));

        // khi thêm () mới tìm được những index phía sau
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[2]"));

    // last
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[last()]"));

    // gần kề last:
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[last()-1]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
