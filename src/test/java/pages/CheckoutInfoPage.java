package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {

    private final By FIRSTNAME_FIELD = By.id("first-name");
    private final By LASTNAME_FIELD = By.id("last-name");
    private final By POSTAL_CODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    private final By TITLE = By.cssSelector("[data-test=title]");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void makeOrder(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickCancel() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
