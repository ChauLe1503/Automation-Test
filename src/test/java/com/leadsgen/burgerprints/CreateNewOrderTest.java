package com.leadsgen.burgerprints;

import com.leadsgen.BaseTest;
import com.leadsgen.data.CreateNewOrder;
import com.leadsgen.page.URL;
import com.leadsgen.page.burgersprint.LoginPageObject;
import com.leadsgen.page.burgersprint.OrderPageObject;
import com.leadsgen.utils.AllureReportListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static com.leadsgen.data.CreateNewOrder.*;
import static com.leadsgen.page.burgersprint.OrderPageObject.getProductDetailsForSKU;
import static com.leadsgen.utils.driver.DriverHolder.getDriver;

@Listeners(AllureReportListener.class)
public class CreateNewOrderTest extends BaseTest {

    LoginPageObject loginPage;
    OrderPageObject orderPageObject;
    SoftAssert softAssert = new SoftAssert();
    CreateNewOrder createNewOrder;
    String expectedFullName = "AlexNguyen";
    String expectedEmail = "AlexNguyen@gmail.com";
    String expectedPhoneNumber = "1234567";
    String expectedCity = "Arizona,";
    String expectedAddress = "Arizona, AZ 85123";
    String expectedCountry = "US";

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPageObject(getDriver());
        orderPageObject = new OrderPageObject(getDriver());
        createNewOrder = new CreateNewOrder();
        getDriver().get(URL.BURGERPRINTS_URL);
        loginPage.loginUserNamePassWord();
    }

    @Test
    @Description("Check price on UI with different sizes")
    public void TC1_VerifyInformationCreateOrderCustom() {
        orderPageObject.createNewOrderWithBaseLocationDenali();
        List<CreateNewOrder> productList = createNewOrder.createOrderData();
        OrderPageObject orderPageObject = new OrderPageObject(getDriver(),productList);
        String result = orderPageObject.selectColorAndSizeAndVerify("Denali - US");
        System.out.println("Verification Passed: \n" + result);
    }

    @Test
    @Description("Check information after creating a new order custom")
    public void TC2_VerifyInformationCreateOrderCustom() {
        orderPageObject.createNewOrderCustom();
        String uiSKU = orderPageObject.getValueSKU();
        System.out.println("Get sku on UI: " + uiSKU);
        List<CreateNewOrder> productList = createNewOrder.createOrderData();
        String shippingFeeUI = orderPageObject.getShippingFee();
        System.out.println("Get Shipping fee on UI: " + shippingFeeUI);
        String shippingFeeCSV = getShippingFeeForSKU(productList, uiSKU);
        System.out.println("Get Shipping fee value in csv file: " + shippingFeeCSV);
        softAssert.assertEquals(shippingFeeUI, shippingFeeCSV, "The value on the UI matches the value in the csv file");
        Allure.addAttachment("Soft Assert Message", "The value on the UI is not correct with the value in the csv file");
        OrderPageObject orderPageObject = new OrderPageObject(getDriver(),productList);
        String skuCost = orderPageObject.getCostWithSKU();
        System.out.println("Get skuCost value UI: " + skuCost);
        String skuCostCSV = getCostForSKU(productList, uiSKU);
        System.out.println("Get skuCost value in csv file: " + skuCostCSV);
        softAssert.assertEquals(skuCost, skuCostCSV, "The value skuCost on the UI matches the value in the csv file");
        String subTotalUI = orderPageObject.getValueTotalCost();
        System.out.println("Get subtotal value on UI: " + subTotalUI);
        String subTotalCSV = getCostForSKU(productList, uiSKU);
        System.out.println("Get subtotal value in csv file: " + subTotalCSV);
        softAssert.assertEquals(subTotalUI, subTotalCSV, "The value subtotal on the UI matches the value in the csv file");
        String fullName = orderPageObject.getValueName();
        softAssert.assertEquals(fullName, expectedFullName,"FullName matches input data.");
        String[] result = orderPageObject.getTextOrderPageCustomProduct();
        System.out.println("Base info product UI : " + result[0]);
        System.out.println("Color UI : " +  result[1]);
        System.out.println("Size UI : " + result[2]);
        String[] productDetailsFromList = getProductDetailsForSKU(productList, uiSKU);
        System.out.println("Base product name in csv : " + productDetailsFromList[0]);
        System.out.println("Color in csv : " + productDetailsFromList[1]);
        System.out.println("Size in csv : " + productDetailsFromList[2]);
        softAssert.assertEquals(result[0], productDetailsFromList[0],"Base matches input data.");
        softAssert.assertEquals(result[1], productDetailsFromList[1],"Color matches input data.");
        softAssert.assertEquals(result[2], productDetailsFromList[2],"Size matches input data.");
        List<String> information = orderPageObject.getInformation();
        System.out.println("Get information on UI: " + information);
        softAssert.assertEquals(information.get(0), expectedEmail,"Email on UI matches input data.");
        softAssert.assertEquals(information.get(1), expectedPhoneNumber,"PhoneNumber on UI matches input data.");
        softAssert.assertEquals(information.get(2), expectedCity,"City on UI matches input data.");
        softAssert.assertEquals(information.get(3), expectedAddress,"Address on UI matches input data.");
        softAssert.assertEquals(information.get(4), expectedCountry,"Country on UI matches input data.");
        softAssert.assertAll();
    }
}
