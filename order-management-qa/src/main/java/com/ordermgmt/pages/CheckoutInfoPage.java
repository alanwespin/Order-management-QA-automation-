package com.ordermgmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CheckoutInfoPage — the shipping/customer-info step of checkout.
 * Boundary/edge-case tests (empty fields, special characters, long strings)
 * target the methods here.
 */
public class CheckoutInfoPage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage fillInfoAndContinue(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public String getValidationError() {
        return isDisplayed(errorMessage) ? getText(errorMessage) : "";
    }
}
