package com.ordermgmt.tests;

import com.ordermgmt.pages.LoginPage;
import com.ordermgmt.pages.InventoryPage;
import com.ordermgmt.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * LoginTest — covers valid login, invalid credentials, locked-out user,
 * and empty-field boundary cases. Maps to Test Case Suite IDs TC_LOGIN_01–06.
 */
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo(BASE_URL);
    }

    @Test(priority = 1, description = "TC_LOGIN_01 - Valid login lands on inventory page")
    public void validLoginSucceeds() {
        InventoryPage inventory = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Expected redirect to inventory page after valid login");
    }

    @Test(priority = 2, description = "TC_LOGIN_04 - Locked-out user is blocked with a clear error")
    public void lockedOutUserIsBlocked() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("locked out"),
                "Expected a locked-out error message");
    }

    @Test(priority = 3, dataProvider = "invalidCredentials",
          description = "TC_LOGIN_02/03/05/06 - Negative and boundary login cases")
    public void invalidLoginIsRejected(String username, String password, String scenario) {
        loginPage.loginAs(username, password);
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(), "Expected an error for scenario: " + scenario);
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"wrong_user", "secret_sauce", "invalid username"},
                {"standard_user", "wrong_pass", "invalid password"},
                {"", "secret_sauce", "empty username (boundary)"},
                {"standard_user", "", "empty password (boundary)"},
        };
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
