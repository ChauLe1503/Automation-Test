package com.leadsgen.page;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasePage {

  private long longTimeouts = URL.PAGE_LOAD_TIMEOUT;

  public static BasePage getBasePageInstance() {
    return new BasePage();
  }

  private By getByLocator(String locator) {
    By by = null;
    if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
      by = By.id(locator.substring(3));
    } else if (locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
      by = By.className(locator.substring(6));
    } else if (locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
      by = By.name(locator.substring(5));
    } else if (locator.startsWith("css=") || locator.startsWith("Css=") || locator.startsWith("CSS=")) {
      by = By.cssSelector(locator.substring(4));
    } else if (locator.startsWith("xpath=") || locator.startsWith("XPath=") || locator.startsWith("XPATH=")
        || locator.startsWith("Xpath=")) {
      by = By.xpath(locator.substring(6));

    } else {
      throw new RuntimeException("Locator is not valid!");
    }
    return by;

  }

  // Hàm chờ cho element hiển thị
  public void waitForElementVisible(WebDriver driver, String locator) {
    new WebDriverWait(driver, longTimeouts)
        .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
  }

    public WebElement getWebElement(WebDriver driver, String locator) {
    return driver.findElement(getByLocator(locator));
  }

  // hàm dùng chung để click vào bất kỳ 1 element nào
  public void clickToElement(WebDriver driver, String locator) {
    getWebElement(driver, locator).click();
  }

  // hàm dùng chung để sendkey vào bất kỳ 1 element nào
  public void sendKeyToElement(WebDriver driver, String locator, String valueToInput) {
    WebElement element = getWebElement(driver, locator);
    element.clear();
    element.sendKeys(valueToInput);
  }

  public String getElementText(WebDriver driver, String locator) {
    return getWebElement(driver, locator).getText();
  }

  public List<WebElement> getListElement(WebDriver driver, String locator) {
    return driver.findElements(getByLocator(locator));
  }


  public void scrollToElementOnTop(WebDriver driver, String locator) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
        getWebElement(driver, locator));
  }

  public void scrollToElementOnDown(WebDriver driver, String locator) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
        getWebElement(driver, locator));
  }

  // Hàm click vào 1 button sử dụng thư viện của javascript
  public void clickToElementByJS(WebDriver driver, String locator) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
  }

  public static void sleepFor(long milliseconds) {
    try {
      Thread.sleep(milliseconds * 1000); // Tạm dừng luồng hiện tại
    } catch (InterruptedException e) {
      // Xử lý khi luồng bị ngắt
      System.err.println("Luồng đã bị ngắt: " + e.getMessage());
    }
  }
}
