package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By FINISH_BUTTON = By.cssSelector("[data-test=finish]");
    private final By CANCEL_BUTTON = By.cssSelector("[data-test=cancel]");
    private final String OVERVIEW_ITEM_NAME = "//*[text()='%s'][@class='inventory_item_name']";
    private final String OVERVIEW_ITEM_PRICE = "//*[text()='%s'][@class='inventory_item_price']";

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getOverviewItemName(String product) {
        return driver.findElement(By.xpath(String.format(OVERVIEW_ITEM_NAME, product))).getText();
    }

    public String getOverviewItemPrice(String product) {
        return driver.findElement(By.xpath(String.format(OVERVIEW_ITEM_PRICE, product))).getText().replace("$", "");
    }

    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void clickCancel() {
        driver.findElement(CANCEL_BUTTON).click();
    }
}
