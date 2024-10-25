package com.leadsgen;

import com.leadsgen.utils.driver.WebDriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import static com.leadsgen.utils.driver.DriverHolder.getDriver;
import static com.leadsgen.utils.driver.DriverHolder.setDriver;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void before() throws IOException {
        setDriver(WebDriverFactory.getDriverInstance());
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
