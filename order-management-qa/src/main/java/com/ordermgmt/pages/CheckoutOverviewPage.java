package com.ordermgmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CheckoutOverviewPage — order summary + final "Finish" step.
 * Also exposes the post-order confirmation banner used to verify the
 * order actually went through (paired with the DB check in the guide).
 */
public class CheckoutOverviewPage extends BasePage {

    private final By totalLabel = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getOrderTotal() {
        return getText(totalLabel);
    }

    public void finishOrder() {
        click(finishButton);
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }
}
