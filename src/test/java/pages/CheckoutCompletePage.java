package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By BACK_HOME_BUTTON = By.cssSelector("[data-test=back-to-products]");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickBackHome() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }
}
