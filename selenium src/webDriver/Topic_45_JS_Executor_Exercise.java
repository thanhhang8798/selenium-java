package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_45_JS_Executor_Exercise {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        emailAddress = "hang" + new Random().nextInt(9999) + "@gmail.net";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_TechPanda() {
        // get url
        navigateToUrlByJS("http://live.techpanda.org/");

        // dùng biến jsExecutor
        jsExecutor.executeScript("return document.domain");
        jsExecutor.executeScript("return document.URL");
        jsExecutor.executeScript("return document.title");

        // dùng hàm tiện hơn (hàm được viết ở AfterClass)
        // get domain
        // vì executeForBrowser đang là kiểu Option nhưng lại khai báo biến techPandaDomain kiểu String nên phải tiến hành ép kiểu dữ liệu
        String techPandaDomain = (String) executeForBrowser("return document.domain");
        System.out.println(techPandaDomain);
        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        // get URL
        Assert.assertEquals(executeForBrowser("return document.URL"),"http://live.techpanda.org/");
        executeForBrowser("return document.title");

        // click vào mobile
        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        // click vào add to card của samsung galaxy
        clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//button");

        // verify msg hiển thị
        // cách 1: verify tương đối
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
        // cách 2: verify tương đối, dùng hàm executeForBrowser nhưng phải ép kiểu dữ liệu
        String verifyText = (String) executeForBrowser("return document.documentElement.innerText");
        Assert.assertTrue(verifyText.contains("Samsung Galaxy was added to your shopping cart."));
        // cách 3: verify tuyệt đối => nên dùng cách này
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Samsung Galaxy was added to your shopping cart.");

        // click vào customer service
        clickToElementByJS("//a[text()='Customer Service']");

        // verify title của trang customer service
        Assert.assertEquals(executeForBrowser("return document.title"),"Customer Service");

        // scroll xuống textbox newsletter
        scrollToElementOnTop("//input[@id='newsletter']");

        // input email
        sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);

        // click vào subcribe
        clickToElementByJS("//button[@title='Subscribe']");

        // verify text hiển thị
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']"),"Thank you for your subscription.");

        // navigate tới domain fb
        navigateToUrlByJS("https://www.facebook.com/");

        // verify domain sau khi navigate
        Assert.assertEquals(executeForBrowser("return document.domain"),"www.facebook.com");
    }

    @Test
    public void TC_02_html5() {
        driver.get("https://warranty.rode.com/login");
        driver.findElement(By.xpath("//a[contains(.,'Create an Account')]")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // verify name
        // cách 1: verify msg bằng cách dùng DOM property, selenium 4.x => nên dùng cách này
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='name']"))
                .getDomProperty("validationMessage"),"Vui lòng điền vào trường này.");

        // cách 2: dùng JS, selenium 3.x
        Assert.assertEquals(getElementValidationMessage("//input[@id='name']"), "Vui lòng điền vào trường này.");

        // verify email
        // không nhập email
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("abcdef");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Vui lòng điền vào trường này.");

        // nhập email sai định dạng
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thanhhang8798@-gmail.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Vui lòng điền một địa chỉ email.");

        // verify password
        // bỏ trống password
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thanhhang87982@gmail.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"),"Vui lòng điền vào trường này.");

        // verify confirm password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='password_confirmation']"),"Vui lòng điền vào trường này.");

        // confirm pw khác pư
        driver.findElement(By.xpath("//input[@id='password_confirmation']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementTextByJS("//input[@id='password']/following-sibling::div/p"),"The password confirmation does not match.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 3px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}