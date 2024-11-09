package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // tương tác với Browser thông qua biên driver
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        // hàm dùng driver. (chỉ dùng chữ in đậm)

    //*** hàm truy cập url, hàm get dùng để tương tác
        driver.get("https://github.com/SeleniumHQ/selenium/releases");

    // hàm đóng trang web
        //*** quit dùng để out tất cả các tramg đang mở
        driver.quit();
        // close để đóng 1 trang hiện tại
        driver.close();


    //*** tìm element với locator là tham số truyền vào
        // tìm 1 element
        driver.findElement(By.cssSelector(""));
        // tìm nhều element: tìm nhiều checkbox, đường link, nhiều row trong table...
        driver.findElements(By.cssSelector(""));


    // hàm get dùng để lấy dữ liệu
        // TH1 lưu vào đâu đó để sử dụng sau (khai báo biến để sử dụng được nhiều lần sau)
        // lấy ra url ở page hiện tại và lưu vào homePageUrl dưới dạng string
        String homePageUrl = driver.getCurrentUrl();
        driver.get(homePageUrl); // step sau, gọi homePageUrl để sử dụng

        // TH2 lấy dữ liệu ra và sử dụng luôn mà không cần lưu trữ => dùng assert
        Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/SeleniumHQ/selenium/releases");

        // lấy ra title ở page hiện tại: tên của trang trên thanh công cụ của trình duyệt
        driver.getTitle();

        // lấy ra window id ở page hiện tại: so sánh giữa các trang với nhau
        driver.getWindowHandle();

        // lấy ra tất cả các window id của các tab/window
        driver.getWindowHandles();

        // lấy ra source code của page hiện tại: lấy ra code html/css/js
        driver.getPageSource();


    //*** hàm switchTo: dùng để handle alert - frame/iFrame - window/tab
        // alert
        driver.switchTo().alert();

        // frame / iFrame
            // switch vào frame/ iFrame (kiểu popup)
            driver.switchTo().frame("");
            // switch ra để trở lại trang ban đầu
            driver.switchTo().defaultContent();
            // switch từ frame con ra frame cha (nhiều frame lồng nhau)
            driver.switchTo().parentFrame();

        // window: trang, dịch chuyển từ trang này sang trang khác
        driver.switchTo().window("");
            // có thể mở 1 trang mới, chỉ dùng được cho selenium version 4 trở lên
            driver.switchTo().newWindow(WindowType.TAB).get("https://github.com/SeleniumHQ/selenium/releases");
            driver.switchTo().newWindow(WindowType.WINDOW).get("https://github.com/SeleniumHQ/selenium/releases");


    // hàm manage
        // set timeout để tìm element (áp dụng cho cả 2 hàm findElement và findElements)
        //*** dùng để khi không tìm thấy element thì hết chừng đó thời gian rồi mới show lỗi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // có thể set theo ngày/ hour/ phút/...

        // set time out để page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofHours(1));

        // set timeout để chờ cho đoạn code JS được thực thi thành công
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(10));

        // cookie: bỏ qua bước login mà sử dụng luôn cookie để đỡ mất thời gian
        driver.manage().getCookies();
        // driver.manage().addCookie();

        //*** window: phóng to/thu nhỏ trang (fullscreen/ maximize/ minimize)
        driver.manage().window().fullscreen(); // full viền trình duyệt
        driver.manage().window().maximize(); // mở trang trình duyệt to nhất
        driver.manage().window().minimize(); // mở trang trình duyệt nhỏ nhất

            // set dimension: set window/browser có kích thước cụ thể => dùng để test responsive
            driver.manage().window().setSize(new Dimension(1920,1080));
            driver.manage().window().getSize(); // lấy kích thước ra

            // set position: set browser ở vị trí nào trên màn hình
            driver.manage().window().setPosition(new Point(0,100));
            driver.manage().window().getPosition(); // lấy vị trí ra

        // log
        driver.manage().logs().get(LogType.BROWSER);
        driver.manage().logs().get(LogType.CLIENT);
        driver.manage().logs().get(LogType.PERFORMANCE);
        driver.manage().logs().get(LogType.SERVER);

            // lấy tất cả logs hiện có
            driver.manage().logs().getAvailableLogTypes();


    // hàm navigate: chuyển qua chuyển lại các trang
        // quay lại trang trước đó
        driver.navigate().back();

        // chuyển tiếp đến trang trước đó (trang vừa thao tác xong)
        driver.navigate().forward();

        // refresh lại trang hieện tại
        driver.navigate().refresh();

        // mở 1 url
        driver.navigate().to("https://github.com/SeleniumHQ/selenium/releases");

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
