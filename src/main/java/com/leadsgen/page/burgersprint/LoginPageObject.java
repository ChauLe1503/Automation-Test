package com.leadsgen.page.burgersprint;

import com.leadsgen.page.BasePage;
import com.leadsgen.locator.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

  WebDriver driver;

  public LoginPageObject(WebDriver driver) {
    this.driver = driver;
  }

  @Step
  public void inputToEmailTextbox(String username) {
    waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
    sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, username);
  }
  @Step
  public void inputToPassWordTextbox(String password) {
    waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
    sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
  }
  @Step
  public void clickLoginButton() {
    waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
    clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
  }
  @Step
  public void clickLoginButtonContinue() {
    waitForElementVisible(driver, LoginPageUI.CONTINUE_BUTTON);
    clickToElement(driver, LoginPageUI.CONTINUE_BUTTON);
  }

  public LoginPageObject loginPage(String email, String password) {
    inputToEmailTextbox(email);
    clickLoginButton();
    inputToPassWordTextbox(password);
    clickLoginButtonContinue();
    return new LoginPageObject(driver);
  }

  public void loginUserNamePassWord() {
    loginPage("chault@leadsgen.asia", "Name1234@");
  }
}
