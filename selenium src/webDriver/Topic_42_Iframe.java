package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_42_Iframe {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Switch_To_Iframe() {
        driver.get("https://embedgooglemap.net/");
        driver.switchTo().frame(0); // swich vào iframe đầu tiên => không nên dùng
        driver.switchTo().frame("gmap_canvas"); // không nên dùng
        // nên dùng find element: tìm đến thẻ iframe
        driver.switchTo().frame(driver.findElement(By.className("div.gmap_canvas>iframe")));
    }

    @Test
    public void TC_02_Handle_Iframe() {
        // web A chứa iframe B
        driver.get("https://embedgooglemap.net/");

        // switch vào iframe B
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas>iframe")));

        // handle element trong iframe B
        String address = driver.findElement(By.cssSelector("div.address")).getText();
        System.out.println(address);

        // iframe B chứa iframe C
        // switch vào iframe C
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div>iframe")));

        // handle element trong iframe C
        System.out.println(driver.getPageSource());

        // từ iframe C quay lại iframe B
        driver.switchTo().parentFrame();

        // handle element trong iframe B
        String placeName = driver.findElement(By.cssSelector("div.place-name")).getText();
        System.out.println(placeName);

        // từ iframe B quay lại trang web A
        driver.switchTo().defaultContent();

        // handle element trong website A
        driver.findElement(By.id("s")).clear();
        driver.findElement(By.id("s")).sendKeys("47 Nguyễn Tuan");
    }

    @Test
    public void TC_03_Formsite() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("button.osano-cm-accept-all")).click();

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);

        // switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        // chọn dropdown
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Junior");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("Off Campus");

        // click radio gender, btn submit
        // thẻ input bị che bởi thẻ label => dùng thẻ label để click
        driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-4_1']")).click();
        driver.findElement(By.cssSelector("input.submit_button")).click();
        Thread.sleep(3000);

        // switch vào web
        driver.switchTo().defaultContent();

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-white")));

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.id("message-error")).getText(),"Username and password are both required.");
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
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
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
