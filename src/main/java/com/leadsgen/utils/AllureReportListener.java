package com.leadsgen.utils;


import com.leadsgen.utils.driver.DriverHolder;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureReportListener implements ITestListener {

  //Text attachments for Allure
  @Attachment(value = "{0}", type = "text/plain")
  public static String saveTextLog(String message) {
    return message;
  }

  //Screenshot attachments for Allure
  @Attachment(value = "Page screenshot", type = "image/png")
  public static byte[] saveScreenshotPNG() {
    return ((TakesScreenshot) DriverHolder.getDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void onTestFailure(ITestResult result) {
    //Allure Report
    AllureReportListener.saveScreenshotPNG();
    AllureReportListener.saveTextLog(result.getName() + " failed and screenshot taken!");
  }
}