package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By CART_LINK = By.cssSelector("[data-test=shopping-cart-link]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы ProductsPage")
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление в корзину товара с именем '{product}'")
    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    @Step("Нажатие на иконку 'Cart' для перехода в корзину")
    public void clickCart() {
        driver.findElement(CART_LINK).click();
    }
}
