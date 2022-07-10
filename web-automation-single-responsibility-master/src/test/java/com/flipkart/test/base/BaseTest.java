package com.flipkart.test.base;

import com.flipkart.framework.driver.CapabilityFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

   

    protected WebDriver driver;
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    @BeforeTest
    @Parameters(value={"browser"})
    public void setupDriver (String browser) throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser));
    }

    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }

    
}
