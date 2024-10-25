package com.leadsgen.page.burgersprint;

import com.leadsgen.data.CreateNewOrder;
import com.leadsgen.locator.OrderSprintUI;
import com.leadsgen.page.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class OrderPageObject extends BasePage {

  WebDriver driver;

  SoftAssert softAssert = new SoftAssert();
  private List<CreateNewOrder> createNewOrderData;
  private String selectedColor;
  private String selectedSize;
  private static final List<String> SIZE_ORDER = Arrays.asList("S", "M", "L", "XL", "2XL", "3XL", "4XL", "5XL");

  public OrderPageObject(WebDriver driver) {
    this.driver = driver;
  }

  public void clickButton(String ui) {
    waitForElementVisible(driver, ui);
    clickToElementByJS(driver, ui);
  }

  @Step
  public void clickMenuOrder() {
    clickButton(OrderSprintUI.MENU_ORDER);
  }

  @Step
  public void clickButtonCreateOrder() {
    clickButton(OrderSprintUI.MENU_CREATE_ORDER);
  }

  @Step
  public void clickButtonBrowseProduct() {
    clickButton(OrderSprintUI.BROWSE_PRODUCT);
  }

  @Step
  public void clickButtonAddCustomProduct() {
    clickButton(OrderSprintUI.ADD_CUSTOMER_PRODUCT_BUTTON);
  }

  @Step
  public void clickImageProduct() {
    clickButton(OrderSprintUI.IMG_PRODUCT);
  }

  @Step
  public void clickButtonUpdateProduct() {
    clickButton(OrderSprintUI.UPDATE_PRODUCT_BUTTON);
  }

  @Step
  public String getTextTittle() {
    waitForElementVisible(driver, OrderSprintUI.ADD_CUSTOMER_PRODUCT_TITLE);
    return getElementText(driver, OrderSprintUI.ADD_CUSTOMER_PRODUCT_TITLE);
  }

  @Step
  public void scrollToElementSize() {
    waitForElementVisible(driver, OrderSprintUI.SIZE);
    scrollToElementOnDown(driver, OrderSprintUI.SIZE);
  }

  @Step
  public void clickToColor() {
    waitForElementVisible(driver,OrderSprintUI.COLOR);
    List<WebElement> colors = getListElement(driver,OrderSprintUI.COLOR);
    sleepFor(1);
    if (!colors.isEmpty()) {
      Random random = new Random();
      int randomIndex = random.nextInt(colors.size());
      WebElement randomOption = colors.get(randomIndex);
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
      Actions actions = new Actions(driver);
      actions.moveToElement(randomOption).click().perform();
      selectedColor = randomOption.getText().trim();
      System.out.println("Get random color on ui : " + selectedColor);
    }
  }


  @Step
  public void clickToSize() {
    waitForElementVisible(driver,OrderSprintUI.SIZE);
    sleepFor(1);
    List<WebElement> sizes = getListElement(driver,OrderSprintUI.SIZE);
    Random random = new Random();
    int randomIndex = random.nextInt(sizes.size());
    WebElement randomOption = sizes.get(randomIndex);
    Actions actions = new Actions(driver);
    actions.moveToElement(randomOption).click().perform();
    selectedSize = randomOption.getText().trim();
    System.out.println("Get random size on ui: " + selectedSize);
  }


  @Step
  public void clickUploadDesignFront() {
    waitForElementVisible(driver, OrderSprintUI.BUTTON_UPLOAD_DESIGN_FONT);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_UPLOAD_DESIGN_FONT);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().perform();
  }

  @Step
  public void clickUploadedImage() {
    waitForElementVisible(driver, OrderSprintUI.BUTTON_UPLOAD_IMAGE);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_UPLOAD_IMAGE);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public void clickUploadDesignBack() {
    waitForElementVisible(driver, OrderSprintUI.BUTTON_UPLOAD_DESIGN_BACK);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_UPLOAD_DESIGN_BACK);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public void clickCheckBoxButton() {
    waitForElementVisible(driver, OrderSprintUI.CHECKBOX);
    WebElement element = getWebElement(driver,OrderSprintUI.CHECKBOX);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public void scrollToButtonCheckBox() {
    scrollToElementOnDown(driver, OrderSprintUI.CHECKBOX);
  }

  @Step
  public void scrollToRadioButton() {
    waitForElementVisible(driver, OrderSprintUI.RADIO_BUTTON_STANDARD);
    sleepFor(3);
    scrollToElementOnDown(driver, OrderSprintUI.RADIO_BUTTON_STANDARD);
  }

  @Step
  public void scrollUpToButtonShippingInfo() {
    waitForElementVisible(driver, OrderSprintUI.SHIPPING_INFO);
    sleepFor(1);
    scrollToElementOnTop(driver, OrderSprintUI.SHIPPING_INFO);
  }

  @Step
  public void clickRadioButtonWithBaseDenali() {
    waitForElementVisible(driver,OrderSprintUI.RADIO_BUTTON_STANDARD);
    WebElement element = getWebElement(driver,OrderSprintUI.RADIO_BUTTON_STANDARD);
    sleepFor(1);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public void clickButtonSave() {
    waitForElementVisible(driver,OrderSprintUI.BUTTON_SAVE);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_SAVE);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public void clickButtonEdit() {
    waitForElementVisible(driver,OrderSprintUI.BUTTON_EDIT);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_EDIT);
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().build().perform();
  }

  @Step
  public String inputFulName(String fullName) {
    waitForElementVisible(driver, OrderSprintUI.FULL_NAME);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.FULL_NAME, fullName);
    return fullName;
  }

  @Step
  public String inputEmail(String email) {
    waitForElementVisible(driver, OrderSprintUI.EMAIL);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.EMAIL, email);
    return email;
  }

  @Step
  public String inputPhoneNumber(String phonenumber) {
    waitForElementVisible(driver, OrderSprintUI.PHONE_NUMBER);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.PHONE_NUMBER, phonenumber);
    return phonenumber;
  }

  @Step
  public String inputCity(String city) {
    waitForElementVisible(driver, OrderSprintUI.CITY);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.CITY, city);
    return city;
  }

  @Step
  public void selectedBaseDropdown(String valueItem) {
    Actions actions = new Actions(driver);
    waitForElementVisible(driver,OrderSprintUI.BASE_BUTTON);
    WebElement element = getWebElement(driver,OrderSprintUI.BASE_BUTTON);
    element.click();
    List<WebElement> options = getListElement(driver,OrderSprintUI.LIST_BASE_VALUE_BUTTON);
    for (WebElement option : options) {
      if (option.getText().equals(valueItem)) {
        actions.moveToElement(option).click().perform();
        break;
      }
    }
  }

  @Step
  public void selectedCountryDropdown(String value) {
    Actions actions = new Actions(driver);
    waitForElementVisible(driver,OrderSprintUI.COUNTRY);
    WebElement element = getWebElement(driver,OrderSprintUI.COUNTRY);
    actions.moveToElement(element).click().perform();
    List<WebElement> options = getListElement(driver,OrderSprintUI.LIST_COUNTRY_ELEMENT);
    for (WebElement option : options) {
      String result = option.getText();
      if (result.equals(value)) {
        actions.moveToElement(option).click().perform();
        break;
      }
    }
  }

  @Step
  public void selectedStateDropdown(String valueState) {
    Actions actions = new Actions(driver);
    waitForElementVisible(driver,OrderSprintUI.STATE);
    WebElement element = getWebElement(driver,OrderSprintUI.STATE);
    sleepFor(1);
    actions.moveToElement(element).click().perform();
    List<WebElement> options = getListElement(driver,OrderSprintUI.LIST_STATE_ELEMENT);
    for (WebElement option : options) {
      if (option.getText().equals(valueState)) {
        option.click();
        break;
      }
    }
  }

  @Step
  public String inputStressAddress(String Address) {
    waitForElementVisible(driver, OrderSprintUI.STRESS_ADDRESS);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.STRESS_ADDRESS, Address);
    return Address;
  }

  @Step
  public String inputZipCode(String zipcode) {
    waitForElementVisible(driver, OrderSprintUI.ZIP_CODE);
    sleepFor(2);
    sendKeyToElement(driver, OrderSprintUI.ZIP_CODE, zipcode);
    return zipcode;
  }

  @Step
  public String[] getTextOrderPageCustomProduct() {
    String actualBaseSizeColor = driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[contains(@class,'cdk-column-product mat-column-product')]/div[contains(@class,'ng-star-inserted')]")).getText();
    int lastHyphenIndex = actualBaseSizeColor.lastIndexOf("-");
    // Lấy phần size từ dấu "-" cuối cùng đến hết chuỗi
    String size = actualBaseSizeColor.substring(lastHyphenIndex + 1).trim();
    // Lấy phần trước dấu "-" cuối cùng (phần base product + color)
    String baseAndColor = actualBaseSizeColor.substring(0, lastHyphenIndex).trim();
    // Tìm dấu "-" trước đó để tách color
    int secondLastHyphenIndex = baseAndColor.lastIndexOf("-");
    // Lấy phần color
    String color = baseAndColor.substring(secondLastHyphenIndex + 1).trim();
    // Lấy phần base product
    String baseProduct = baseAndColor.substring(0, secondLastHyphenIndex).trim();
    // Trả về cả 3 phần
    return new String[]{baseProduct, color, size};
  }

  @Step
  public String getValueCost() {
    sleepFor(1);
    String cost = getElementText(driver,OrderSprintUI.UI_COST);
    if (cost.startsWith("$")) {
      cost = cost.substring(1);
    }
    if (cost.endsWith("0")) {
      cost = cost.substring(0, cost.length() - 1);
    }
    return cost;
  }

  @Step
  public String getValueTotalCost() {
    sleepFor(1);
    String cost = getElementText(driver,OrderSprintUI.UI_TOTAL_COST);
    if (cost.startsWith("$")) {
      cost = cost.substring(1);
    }
    if (cost.endsWith(".00")) {
      cost = cost.substring(0, cost.length() - 3);
    }
    return cost;
  }

  public String getFromPrice() {
    return getElementText(driver,OrderSprintUI.FROM_PRICE_UI).replaceAll("[$]", "");
  }

  @Step
  public String getCostWithSKU() {
    String skuValueCost = getElementText(driver,OrderSprintUI.SKU_COST_VALUE);
    return skuValueCost.replaceAll("[$]", "").substring(skuValueCost.indexOf(":") + 1).trim().replaceAll("\\.00", "");
  }

  @Step
  public List<String> getInformation() {
    waitForElementVisible(driver,OrderSprintUI.BUTTON_INFORMATION);
    List<WebElement> infoElements = getListElement(driver,OrderSprintUI.BUTTON_INFORMATION);
    List<String> infoList = new ArrayList<>();
    for (WebElement element : infoElements) {
      infoList.add(element.getText().trim());
    }
    return infoList;
  }

  @Step
  public String getValueName() {
    return getElementText(driver, OrderSprintUI.BUTTON_SHIPPING_INFO_NAME);
  }


  @Step
  public String getValueSKU() {
    return getElementText(driver, OrderSprintUI.SKU_TEXT).replace("SKU: ", "");
  }

  @Step
  public String getShippingFee() {
    sleepFor(1);
    return getWebElement(driver, OrderSprintUI.SHIPPING_FEE).getAttribute("innerText").replaceAll("[$]", "");
  }

  @Step
  public void selectedFacilityDropdown(String valueItem) {
    Actions actions = new Actions(driver);
    waitForElementVisible(driver,OrderSprintUI.BUTTON_FACILITY);
    WebElement element = getWebElement(driver,OrderSprintUI.BUTTON_FACILITY);
    actions.moveToElement(element).click().perform();
    sleepFor(1);
    List<WebElement> options = getListElement(driver,OrderSprintUI.LIST_VALUE_BUTTON_FACILITY);
    for (WebElement option : options) {
      if (option.getText().equals(valueItem)) {
        actions.moveToElement(option).click().perform();
        break;
      }
    }
  }

  @Step
  public void selectedStyleDropdown(String valueItem) {
    Actions actions = new Actions(driver);
    waitForElementVisible(driver,OrderSprintUI.STYLE_BUTTON);
    WebElement element = getWebElement(driver,OrderSprintUI.STYLE_BUTTON);
    sleepFor(1);
    actions.moveToElement(element).click().perform();
    sleepFor(1);
    List<WebElement> options = getListElement(driver,OrderSprintUI.LIST_VALUE_STYLE_BUTTON);
    for (WebElement option : options) {
      if (option.getText().equals(valueItem)) {
        actions.moveToElement(option).click().perform();
        break;
      }
    }
  }

  public OrderPageObject(WebDriver driver, List<CreateNewOrder> createOrderData) {
    this.driver = driver;
    this.createNewOrderData = createOrderData; // Danh sách từ file CSV
  }

  @Step
  private String getStarterFromCSV(String selectedColor, String selectedSize, String selectedLocation) {
    if (createNewOrderData == null || createNewOrderData.isEmpty()) {
      return null;
    }
    for (CreateNewOrder product : createNewOrderData) {
      // So sánh kích thước và màu sắc
      if (product.getColor().trim().equalsIgnoreCase(selectedColor.trim()) &&
              product.getSize().trim().equalsIgnoreCase(selectedSize.trim()) &&
              product.getLocation().trim().equalsIgnoreCase(selectedLocation.trim())) {
        return product.getStarter();
      }
    }
    return null; // Nếu không tìm thấy
  }

  @Step
  public String selectRandomColorAndSize(String selectedLocation) {
    clickToColor();
    clickToSize();
    // So sánh với file CSV và lấy trường Starter
    return getStarterFromCSV(selectedColor, selectedSize, selectedLocation);
  }

  @Step
  public HashMap<String, String> clickSizeAndSaveCost() {
    HashMap<String, String> sizeCostMap = new HashMap<>();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    // Tìm tất cả các size trên UI
    List<WebElement> sizes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'list-size-wrap')]/li[contains(@class,'mat-tooltip-trigger')]")));
    // Duyệt qua từng size và lấy giá trị cost
    for (WebElement sizeOption : sizes) {
      Actions actions = new Actions(driver);
      actions.moveToElement(sizeOption).click().perform();
      // Lấy size đã click
      selectedSize = sizeOption.getText().trim();
      sleepFor(3);
      // Chờ và lấy giá trị cost trên UI
      WebElement costElement = driver.findElement(By.xpath("//span[text()='From price']/following-sibling::span"));
      String fromPrice = costElement.getText().trim();
      // Lưu giá vào HashMap
      sizeCostMap.put(selectedSize, fromPrice);
    }
    // Trả về HashMap chứa kích thước và giá
    return sizeCostMap;
  }

  private int getSizeOrder(String size) {
    return SIZE_ORDER.indexOf(size);  // Trả về chỉ số của size trong danh sách
  }

  @Step
  public String selectColorAndSizeAndVerify(String selectedLocation) {
    StringBuilder result = new StringBuilder();
    clickToColor();
    HashMap<String, String> sizeCostMap = clickSizeAndSaveCost();
    // Sắp xếp kích thước theo thứ tự mong muốn
    List<String> sortedSizes = new ArrayList<>(sizeCostMap.keySet());
    sortedSizes.sort(Comparator.comparingInt(this::getSizeOrder));  // Sử dụng Comparator tùy chỉnh để sắp xếp
    // Duyệt qua từng size và so sánh với dữ liệu từ file CSV
    for (String size : sortedSizes) {
      String costOnUI = sizeCostMap.get(size).replace("$", "").trim();
      String starterFromCSV = getStarterFromCSV(selectedColor, size, selectedLocation);
      // So sánh giá trị cost trên UI với giá trị trong CSV
      if (costOnUI.equals(starterFromCSV)) {
        result.append("Match for size ").append(size)
                .append(": Cost on UI = ").append(costOnUI)
                .append(", Starter in CSV = ").append(starterFromCSV).append("\n");
        // Log match trong SoftAssert
        softAssert.assertEquals(costOnUI, starterFromCSV, "Match for size: " + size + " - Cost on UI = " + costOnUI + ", CSV = " + starterFromCSV);
        // Ghi match vào Allure
        Allure.step("Match for size: " + size + ", Cost on UI = " + costOnUI + ", CSV = " + starterFromCSV);
      } else {
        result.append("Mismatch for size ").append(size)
                .append(": Cost on UI = ").append(costOnUI)
                .append(", Starter in CSV = ").append(starterFromCSV).append("\n");
        result.append(size).append(costOnUI).append(starterFromCSV).append("\n");
        // Log mismatch trong SoftAssert
        softAssert.assertEquals(costOnUI, starterFromCSV, "Mismatch for size: " + size + " - Cost on UI = " + costOnUI + ", CSV = " + starterFromCSV);

        // Ghi mismatch vào Allure
        Allure.step("Mismatch for size: " + size + ", Cost on UI = " + costOnUI + ", CSV = " + starterFromCSV);
      }
    }
    scrollToElementSize();
    softAssert.assertAll();
    return result.toString();//Trả về kết quả dưới dạng chuỗi
  }

  @Step
  public static String[] getProductDetailsForSKU(List<CreateNewOrder> orderList, String skuToFind) {
    if (orderList == null || orderList.isEmpty() || skuToFind == null || skuToFind.trim().isEmpty()) {
      return null; // Trả về null nếu không có dữ liệu
    }
    // Duyệt qua từng sản phẩm trong danh sách
    for (CreateNewOrder order : orderList) {
      // So sánh SKU (loại bỏ khoảng trắng và không phân biệt chữ hoa/chữ thường)
      if (order.getSku().trim().equalsIgnoreCase(skuToFind.trim())) {
        // Trả về thông tin productName, color và size nếu SKU khớp
        String productName = order.getProductName();
        String color = order.getColor();
        String size = order.getSize();
        return new String[]{productName, color, size};
      }
    }
    // Trả về null nếu không tìm thấy SKU khớp
    return null;
  }

  @Step
  public void createNewOrderWithBaseLocationDenali() {
    clickMenuOrder();
    clickButtonCreateOrder();
    clickButtonAddCustomProduct();
    selectedBaseDropdown("Unisex T-Shirt | Gildan 5000 (US)");
    selectedFacilityDropdown("Denali");
  }

  @Step
  public void createNewOrderCustom() {
    clickMenuOrder();
    clickButtonCreateOrder();
    clickButtonAddCustomProduct();
    softAssert.assertEquals(getTextTittle(), "Add custom product");
    selectedBaseDropdown("Unisex T-Shirt | Gildan 5000 (US)");
    selectedFacilityDropdown("Denali");
    clickToColor();
    clickToSize();
    clickUploadDesignFront();
    clickUploadedImage();
    clickUploadDesignBack();
    clickUploadedImage();
    scrollToButtonCheckBox();
    clickCheckBoxButton();
    clickButtonSave();
    clickButtonEdit();
    inputFulName("AlexNguyen");
    inputEmail("AlexNguyen@gmail.com");
    inputPhoneNumber("1234567");
    inputCity("Arizona");
    selectedCountryDropdown("United States");
    selectedStateDropdown("AZ - Arizona");
    inputStressAddress("Arizona");
    inputZipCode("85123");
    clickButtonSave();
    scrollToRadioButton();
    clickRadioButtonWithBaseDenali();
    scrollUpToButtonShippingInfo();
  }
}
