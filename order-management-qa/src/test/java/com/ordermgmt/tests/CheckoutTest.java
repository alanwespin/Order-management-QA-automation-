package com.ordermgmt.tests;

import com.ordermgmt.pages.*;
import com.ordermgmt.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * CheckoutTest — end-to-end order placement plus checkout-form boundary cases.
 * Maps to Test Case Suite IDs TC_CHECKOUT_01–07.
 */
public class CheckoutTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        LoginPage login = new LoginPage(driver);
        login.goTo(BASE_URL);
        login.loginAs("standard_user", "secret_sauce");
    }

    @Test(priority = 1, description = "TC_CHECKOUT_01 - Full happy-path order placement")
    public void completeOrderSuccessfully() {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addProductToCart("sauce-labs-backpack");
        Assert.assertEquals(inventory.getCartCount(), 1, "Cart badge should reflect 1 item");

        CartPage cart = inventory.goToCart();
        CheckoutInfoPage info = cart.proceedToCheckout();
        CheckoutOverviewPage overview = info.fillInfoAndContinue("Alan", "Wespin", "600001");
        overview.finishOrder();

        Assert.assertEquals(overview.getConfirmationMessage(), "Thank you for your order!",
                "Order confirmation message should be displayed");
    }

    @Test(priority = 2, dataProvider = "checkoutBoundaryCases",
          description = "TC_CHECKOUT_04/05/06 - Required-field boundary validation at checkout")
    public void checkoutRequiresAllFields(String first, String last, String postal, String scenario) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addProductToCart("sauce-labs-backpack");
        CartPage cart = inventory.goToCart();
        CheckoutInfoPage info = cart.proceedToCheckout();
        info.fillInfoAndContinue(first, last, postal);

        Assert.assertFalse(info.getValidationError().isEmpty(),
                "Expected a validation error for scenario: " + scenario);
    }

    @DataProvider(name = "checkoutBoundaryCases")
    public Object[][] checkoutBoundaryCases() {
        return new Object[][]{
                {"", "Wespin", "600001", "missing first name"},
                {"Alan", "", "600001", "missing last name"},
                {"Alan", "Wespin", "", "missing postal code"},
        };
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
