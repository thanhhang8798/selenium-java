package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Element() {
    // tương tác trực tiếp lên element
        driver.findElement(By.cssSelector(""));

        // hàm findElement: cho phép tìm liên tiếp element con
        driver.findElement(By.cssSelector("div.form-fields input#FirstName")); // dùng quan hệ element cha-con (phải viết chung kiểu css/xpath)
        driver.findElement(By.cssSelector("div.form-fields"))
                .findElement(By.xpath("//input[@id='FirstName']")); // áp dụng khi element cha-con viết khác kiểu (không bắt buộc phải cùng kiểu css/ xpath)


    // thao tác nhiều lần lên 1 element => nên khai báo biến: hàm trả về biến dạng gì thì phải khai báo biến dưới dạng đó
        String homePageTitle = driver.getTitle(); // hàm getTitle là kiểu string nên phải khai báo biến dưới dạng string

        WebElement firstNameTextbox = driver.findElement(By.cssSelector("input#firstname")); // hàm findElement là kiểu webElement


    // các hàm tương tác với element
        // hàm clear: xóa dữ liệu trong 1 editable element (trường nhập thông tin như textbox/ text area/ dropdown)
        // trước khi nhập dữ liệu mới phải xóa dữ liệu cũ đi để tránh nhaapj chồng lên nhau => dùng hàm clear sau đó dùng hàm sendkeys
        firstNameTextbox.clear();

        //*** hàm sendKeys: để nhập dữ liệu
        firstNameTextbox.sendKeys("Thanh Hằng");

            // nếu không khai báo biến => phải viết đi viết lại
            driver.findElement(By.cssSelector("input#firstname")).clear();
            driver.findElement(By.cssSelector("input#firstname")).sendKeys("Thanh Hằng");


        //*** hàm click: click lên clickable element (button/ checkbox/ radio/ link/ image/ dropdown/ icon)
        firstNameTextbox.click();

        // hàm submit: submit thông tin gửi lên server, giả lập hành vi enter của user (register/ login/ search...)
        // submit chỉ dùng được cho các element ở trong form
        firstNameTextbox.submit();


    // các hàm verify thông tin/ dữ liệu đã action
        //*** hàm isDisplayed: kiểm tra 1 element có hiển thị hay không, áp dụng cho all element
        // khi element không hiển thị => user không nhin thấy trên UI và không thao tác lên được
        firstNameTextbox.isDisplayed(); // kiểu boolean (đúng/sai)

        // hàm isSlected: kiểm tra 1 element đã được chọn hay chưa, chỉ áp dụng cho checkbox/ radio/ dropdown
        firstNameTextbox.isSelected();

        // hàm enabled: kiểm tra element có thao tác được hay không
        // hàm trả về = true thì cho phép chỉnh sửa, hàm trả về = false thì không cho phép => test phân quyền
        firstNameTextbox.isEnabled();


    // các hàm lấy dữ liệu => get
        // getSize: lấy ra chiều rộng, chiều cao của element
        firstNameTextbox.getSize();

        // getLocation: element có vị trí ở đâu trong trình duyệt (sử dụng góc trên bên trái để xem tọa độ màn hình)
        firstNameTextbox.getLocation();

        // getRect: là tổng hợp của size và location
        Rectangle elementRect = firstNameTextbox.getRect(); // vì getRect có kiểu dữ liệu rectangle
        elementRect.getDimension(); // giống getSize
        elementRect.getPoint(); // giống getLocation

        //*** getText: lấy ra text của element
        // chỉ áp dụng cho text nằm ngoài attribute, không dùng cho text placeholder vì nó nằm trong attribute placeholder
        firstNameTextbox.getText();

        // getAtribute: truyền vào tên attribute
        firstNameTextbox.getAttribute("placeholder");

        // shadow DOM: nếu element nằm trong shadow root thì phải dùng hàm này
        firstNameTextbox.getShadowRoot();

        // getCssValue: lấy ra bất cứ gì liên quan đến css (tab styles trong dev tool: font / color/ font size/ background...)
        firstNameTextbox.getCssValue("background-color");
        firstNameTextbox.getCssValue("font-size");

        // getTagName: láy tên thẻ html của element
        firstNameTextbox.getTagName();

        // getScreenshotAs: chụp màn hình khi chạy test bị lỗi
        firstNameTextbox.getScreenshotAs(OutputType.FILE);
        firstNameTextbox.getScreenshotAs(OutputType.BYTES);
        firstNameTextbox.getScreenshotAs(OutputType.BASE64); // được mã hóa thành text

        // dev FE dùng để verify
        // firstNameTextbox.getDomAttribute();
        // firstNameTextbox.getDomProperty(); // html không có thì lấy từ property
        // firstNameTextbox.getAriaRole();

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
