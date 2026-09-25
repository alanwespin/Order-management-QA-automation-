package com.ordermgmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

/**
 * CartPage — models the shopping cart screen and hands off to Checkout.
 */
public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(By.className("inventory_item_name")).stream()
                .map(el -> el.getText())
                .toList();
    }

    public CheckoutInfoPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutInfoPage(driver);
    }
}
