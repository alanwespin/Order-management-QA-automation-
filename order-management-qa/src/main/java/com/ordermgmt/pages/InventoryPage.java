package com.ordermgmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

/**
 * InventoryPage — the product catalog screen. Handles adding items to the
 * cart and navigating to checkout.
 */
public class InventoryPage extends BasePage {

    private final By productNames = By.className("inventory_item_name");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(String productId) {
        click(By.id("add-to-cart-" + productId));
    }

    public void removeProductFromCart(String productId) {
        click(By.id("remove-" + productId));
    }

    public int getCartCount() {
        return isDisplayed(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public List<String> getAllProductNames() {
        return driver.findElements(productNames).stream()
                .map(el -> el.getText())
                .toList();
    }

    public void sortBy(String value) {
        // e.g. "lohi" (price low to high), "hilo", "az", "za"
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(driver.findElement(sortDropdown));
        select.selectByValue(value);
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }
}
