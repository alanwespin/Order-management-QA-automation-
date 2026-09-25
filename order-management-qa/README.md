# Order Management System — QA Automation Framework

Selenium WebDriver + TestNG automation framework built using the Page Object Model,
covering login and checkout regression scenarios for the Order Management System
project (see the accompanying BRD and Test Case Suite).

## Tech Stack
- Java 17
- Selenium WebDriver 4.21
- TestNG 7.10
- Maven
- GitHub Actions (CI)

## Project Structure
```
src/main/java/com/ordermgmt/pages/    Page Object classes
src/main/java/com/ordermgmt/utils/    DriverFactory (Chrome setup, headless-for-CI)
src/test/java/com/ordermgmt/tests/    TestNG test classes (LoginTest, CheckoutTest)
testng.xml                            Suite definition (parallel execution)
.github/workflows/ci.yml              CI pipeline — runs suite on every push
```

## Running Locally
```bash
mvn clean test
```
Requires Chrome installed locally; WebDriverManager handles the driver binary automatically.

## Running in CI
Push to `main` or open a pull request — GitHub Actions runs the full regression
suite headlessly and uploads the Surefire report as a build artifact.

## Test Coverage
- **LoginTest**: valid login, locked-out user, invalid/empty-field boundary cases
- **CheckoutTest**: full happy-path order placement, required-field boundary validation

Maps to Test Case Suite IDs `TC_LOGIN_01–06` and `TC_CHECKOUT_01–07`.
