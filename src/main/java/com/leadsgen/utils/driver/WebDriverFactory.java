package com.leadsgen.utils.driver;

import com.leadsgen.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import java.io.IOException;
import java.util.Properties;

public class WebDriverFactory {

    @Parameters("browser")
    public static WebDriver getDriverInstance() throws IOException {
        Properties props = ConfigReader.getProperties();
        String browserName = props.getProperty("browser").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(props.getProperty("headless"));
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");
        if ("chrome".equals(browserName)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else {
            throw new UnsupportedOperationException("Browser not supported: " + browserName);
        }
        return driver;
    }
}

