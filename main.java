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
    public static void loginResultTest() throws Exception {
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);

        lp.open();
        lp.login(USERNAME, PASSWORD, false);

        hp.waitForPage();
        // verify that home page is visible
        Assert.assertTrue(hp.isPresent());

        hp.logout();
        // verify that we are not on home page anymore
        Assert.assertFalse(hp.isPresent());
    }

    public static void register() throws Exception {
        LoginPage lp = new LoginPage(driver);
        ResetPasswordPage rpp = new ResetPasswordPage(driver);

        lp.open();
        lp.clickCantAccessYourAccountLink();
        rpp.waitForPage();
        rpp.setAccountName(USERNAME);
        rpp.setCaptcha();
        rpp.clickNext();
        boolean res = rpp.selectEmailMeResetLink();
        Assert.assertTrue(res);
    }


    public static void resPassword() throws Exception {
        LoginPage lp = new LoginPage(driver);
        ResetPasswordPage rpp = new ResetPasswordPage(driver);

        lp.open();
        lp.clickCantAccessYourAccountLink();
        rpp.waitForPage();
        rpp.setAccountName(USERNAME);
        rpp.setCaptcha();
        rpp.clickNext();
        boolean res = rpp.selectSecurityQuestion("answer", "new_password");
        Assert.assertTrue(res);
    }


    public void tearDown() throws Exception {
        driver.quit();
    }

}
