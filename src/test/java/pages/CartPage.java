package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final String CART_ITEM_NAME = "//*[text()='%s'][@class='inventory_item_name']";
    private final String CART_ITEM_PRICE = "//*[text()='%s'][@class='inventory_item_price']";
    private final String REMOVE_TO_CART_PATTERN = "//div[@class='cart_item' and .//div[text()='%s']]//button[text()='Remove']";
    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test=checkout]");
    private final By TITLE = By.cssSelector("[data-test=title]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemName(String product) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_NAME, product))).getText();
    }

    public String getCartItemPrice(String product) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_PRICE, product))).getText().replace("$", "");
    }

    public void removeToCart(String product){
        driver.findElement(By.xpath(String.format(REMOVE_TO_CART_PATTERN, product))).click();
    }

    public boolean isProductDisplayed(String productName) {
        return !driver.findElements(By.xpath("//div[text()='" + productName + "']")).isEmpty();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getTitle(){
        return driver.findElement(TITLE).getText();
    }
}
