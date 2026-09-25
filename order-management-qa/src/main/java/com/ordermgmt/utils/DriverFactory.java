package com.ordermgmt.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * DriverFactory — one place to create/configure the WebDriver instance.
 * Keeping this separate from tests is what makes it easy to plug in
 * headless mode for CI (GitHub Actions) without touching any test class.
 */
public class DriverFactory {

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        // CI runners have no display, so headless mode is required there.
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        options.addArguments("--window-size=1400,900");
        return new ChromeDriver(options);
    }
}
