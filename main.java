package com.example.hotmail.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.example.hotmail.model.HomePage;
import com.example.hotmail.model.LoginPage;
import com.example.hotmail.model.ResetPasswordPage;
import junit.framework.TestCase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HotmailTestCases extends TestCase {

    private static final String USERNAME = "username@hotmail.com";

    private static final String PASSWORD = "password";

    private static final String SELENIUM_RC_URL = "http://selenium-hub.dev:4444/wd/hub";

    private static WebDriver driver = null;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    public void setUp() throws Exception {
        DesiredCapabilities capabillities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(SELENIUM_RC_URL), capabillities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    public void tearDown() throws Exception {
        driver.quit();
    }

}
