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
import java.util.Set;

public class Topic_43_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Not_Use_Method() throws InterruptedException {
        // không dùng hàm
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // lấy ra window id của cửa sổ đang active (drive đang đứng ở đó)
        String githubID = driver.getWindowHandle(); // window handle số ít
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // lấy ra window id của 2 cửa sổ/ tab
        Set<String> allWindows = driver.getWindowHandles(); // window handle số nhiều
        for (String window : allWindows) {
            if (!window.equals(githubID)) { // nếu window không phải là githubID
                driver.switchTo().window(window); // chuyển sang window mới
                break; // sau khi chuyển sang window mới rồi thì dừng lại out ra khỏi vòng lặp
            }
        }
        // driver đang ở trang google, lấy ra ID của google
        String googleID = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        // handle element ở window mới
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("study");

        // quay lại trang github
        allWindows = driver.getWindowHandles(); // window handle số nhiều
        for (String window : allWindows) {
            if (!window.equals(googleID)) { // nếu window không phải là google
                driver.switchTo().window(window); // chuyển sang window mới
                break; // sau khi chuyển sang window mới rồi thì dừng lại out ra khỏi vòng lặp
            }
        }
        // driver đang ở trang github
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

    }

    @Test
    public void TC_02_Use_Method_2WindowTab() throws InterruptedException {
        // dùng hàm: chỉ áp dụng cho 2 window hoă 2 tab
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // lấy ra window id của cửa sổ đang active (drive đang đứng ở đó)
        String githubID = driver.getWindowHandle(); // window handle số ít
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        switchToWindowByID(githubID);

        // driver đang ở trang google
        String googleID = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        // handle element ở window mới
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("study");

        // quay lại trang github
        switchToWindowByID(googleID);

        // driver đang ở trang github
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

    }

    // chỉ dùng cho 2 window/ tab
    private void switchToWindowByID(String windowID) {
        // lấy ra window id của 2 cửa sổ/ tab
        Set<String> allWindows = driver.getWindowHandles(); // window handle số nhiều
        for (String window : allWindows) {
            if (!window.equals(windowID)) { // nếu id nào khác với id truyền vào thì...
                driver.switchTo().window(window); // chuyển sang window còn lai
                break; // sau khi chuyển sang window mới rồi thì dừng lại out ra khỏi vòng lặp
            }
        }
    }
    @Test
    public void TC_03_Use_Method_ManyWindowTab() throws InterruptedException {
        // dùng hàm cho nhiều window/ tab
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // lấy ra window id của cửa sổ đang active (drive đang đứng ở đó)
        String githubID = driver.getWindowHandle(); // window handle số ít
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // switch vào google
        switchToWindowByTitle("Google");

        // driver đang ở trang google
        String googleID = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        // handle element ở window mới
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("study");

        switchToWindowByTitle("Selenium WebDriver");
        // driver đang ở trang github
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(3000);

        // switch vào facebook
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // close các window/tab đang mở
        // truyền githubID để giữa lại trang gốc
        closeWindow(githubID);
    }

    private void closeWindow(String windowID) {
        // close tất cả các window/ tab ngoại trừ trang gốc
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(windowID)) { // nếu ID nào khác với ID truyền vào
                driver.switchTo().window(window); // thì switch vào
                driver.close(); // close window/ tab vừa switch vào
            }
        }
        // sau khi close xong phải switch vào trang gốc
        driver.switchTo().window(windowID);
    }

    // dùng cho nhiều window/ tab
    private void switchToWindowByTitle (String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles(); // window handle số nhiều
        for (String window : allWindows) { // window là biến, thay đổi sau mỗi lần duyệt allwwindows
            // switch vào từng window
            driver.switchTo().window(window);
            // lấy ra title của trang vừa switch vào
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(expectedTitle)) { // nếu page title giống với mong đợi
                break; // out ra khỏi vòng lặp
            }
        }
    }

    @Test
    public void TC_04_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        // lấy ra window id của cửa sổ đang active (drive đang đứng ở đó)
        String techPandaID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
                .getText(),"The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
                .getText(),"The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        Thread.sleep(3000);

        // switch vào window mới
        switchToWindowByID(techPandaID);

        // ID của cửa sổ mơới
        String compareWindowID = driver.getWindowHandle();

        // verify title của cửa sổ mới
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        // close window: click nút close window
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

        // switch vào trang ban đầu
        switchToWindowByID(compareWindowID);
        driver.findElement(By.cssSelector("div.actions>a")).click();
        Thread.sleep(3000);

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span"))
                .getText(),"The comparison list was cleared.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
